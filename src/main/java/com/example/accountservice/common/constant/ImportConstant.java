package com.example.accountservice.common.constant;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ImportConstant {
    private ImportConstant() {
    }
    public static final String RUNNING = "RUNNING";
    public static final String COMPLETED = "COMPLETED";
    public static final String FAILED = "FAILED";
    public static final Map<String, String> IMPORT_AGENT_MAPPING_FILE;

    static {
        Map<String, String> importAgentMappingFile = new LinkedHashMap<>();

        IMPORT_AGENT_MAPPING_FILE = Collections.unmodifiableMap(importAgentMappingFile);
    }

    public static final String RESULT_HEADER = "Kết quả";
    public static final int RESULT_COLUMN_NUMBER = 8;
    public static final int AGENT_CODE_COLUMN_NUMBER = 7;
    public static final String MISSING_ORG_CODE = "Thiếu mã tổ chức";
    public static final String ORG_CODE_NOT_FOUND = "Mã tổ chức ko tồn tại";
    public static final String MISSING_FULL_NAME = "Thiếu họ và tên";
    public static final String INCORRECT_FORMAT_NAME = "Tên không đúng định dạng";
    public static final String INCORRECT_FORMAT_PHONE = "Số điện thoại không đúng định dạng";
    public static final String INCORRECT_FORMAT_EMAIL = "Email không đúng định dạng";
    public static final String BRANCH_NOT_EXISTED = "Branch ko tồn tại";
    public static final String MISSING_BOTH_EMAIL_AND_PHONE = "Thiếu email hoặc số điện thoại";
    public static final String SUCCESS = "Thành công";
    public static final String UPDATE_SUCCESS = "Cập nhật thành công";
    public static final String AGENT_CODE_NOT_FOUND = "Mã CTV không tồn tại";
}