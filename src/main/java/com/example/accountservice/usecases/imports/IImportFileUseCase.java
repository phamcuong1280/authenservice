package com.example.accountservice.usecases.imports;

import org.springframework.web.multipart.MultipartFile;

public interface IImportFileUseCase {
    void importFile(MultipartFile file);
}
