package com.example.accountservice.usecases.imports;

import com.example.accountservice.common.constant.ImportConstant;
import com.example.accountservice.common.validation.RegexConstant;
import com.example.accountservice.infrastructure.models.Account;
import com.example.accountservice.infrastructure.models.Profile;
import com.example.accountservice.infrastructure.repository.JpaAccountRepository;
import com.example.accountservice.infrastructure.repository.JpaProfileRepository;
import com.example.accountservice.usecases.model.AgentCreateDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Service
@Slf4j
@AllArgsConstructor
public class ImportFileUseCase implements IImportFileUseCase {
    private final ImportMapper importMapper;
    private final JpaAccountRepository accountRepository;
    private final JpaProfileRepository profileRepository;

    @Override
    public void importFile(MultipartFile file) {
        InputStream input;
        try {
            input = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (Workbook workbook = new XSSFWorkbook(input)) {
            Sheet sheet = workbook.getSheetAt(0);
            process_row:
            for (Row row : sheet) {
                if (isRowEmpty(row)) {
                    continue;
                }
                try {
                    log.info("[IMPORT_AGENT][ImportService] Process row  " + row.getRowNum());
                    var list = new ArrayList<AgentCreateDto>();
                    AgentCreateDto agentRegister = new AgentCreateDto();
                    for (int cellNumber = 0; cellNumber < ImportConstant.RESULT_COLUMN_NUMBER; cellNumber++) {
                        if (processCell(row, agentRegister, cellNumber)) {
                            continue process_row;
                        }
                    }
                    var account = importMapper.toAccount(agentRegister);
                    var profile = importMapper.toProfile(agentRegister);
                    Account save = accountRepository.save(account);
                    Profile save1 = profileRepository.save(profile);
                    log.info("[IMPORT_AGENT][ImportService] Account  " + save);
                    log.info("[IMPORT_AGENT][ImportService] Profile  " + save1);
                } catch (Exception e) {
                    log.error("[IMPORT_AGENT][ImportService] import row num {} error", row.getRowNum(), e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private boolean processCell(Row row, AgentCreateDto agentRegister, int cellNumber) {
        String value = getCellValue(row, cellNumber);
        log.info("[IMPORT_AGENT][ImportService] Process cell {} value {}", cellNumber, value);
        switch (cellNumber) {
            case 0:
                break;
            case 1:
                if (StringUtils.isBlank(value)) {
                    return true;
                }
                agentRegister.setFullName(value);
                break;
            case 2:
                if (StringUtils.isNotBlank(value)) {
                    agentRegister.setName(value);
                }
                break;
            case 3:
                if (StringUtils.isNotBlank(value)) {
                    agentRegister.setLastMiddleName(value);
                }
                break;
            case 4:
                if (checkValidatePhone(row, agentRegister, value)) return true;
                break;
            case 5:
                if (StringUtils.isNotBlank(value)) {
                    agentRegister.setEmail(value);
                }
                break;
            case 6:
                if (!StringUtils.isBlank(value)) {
                    agentRegister.setBranch(value);
                }
                break;
            case 7:
                if (!StringUtils.isBlank(value)) {
                    agentRegister.setAgentCode(value);
                }
                break;
            default:
                break;
        }
        return false;
    }

    private boolean checkValidatePhone(Row row, AgentCreateDto agentUpdate, String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replaceAll("[(\\s)(\\+)]", "").replaceAll("^(84)", "0");
            if (!value.matches(RegexConstant.PHONE_REGEX)) {
                setResultMessage(row, ImportConstant.INCORRECT_FORMAT_PHONE, ImportConstant.RESULT_COLUMN_NUMBER);
                return true;
            }
            agentUpdate.setPhone(value);
        }
        return false;
    }

    private void setResultMessage(Row row, String message, int cellNumber) {
        Cell cell = row.createCell(cellNumber);
        cell.setCellValue(message);
    }

    private String getCellValue(Row row, Integer cellNumber) {
        if (row.getCell(cellNumber) == null || row.getCell(cellNumber).getCellType() == null) {
            return Strings.EMPTY;
        }
        Cell cell = row.getCell(cellNumber);
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return NumberToTextConverter.toText(cell.getNumericCellValue()).trim();
            default:
                return cell.getStringCellValue().trim();
        }
    }
}
