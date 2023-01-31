package com.example.accountservice.controllers;

import com.example.accountservice.usecases.imports.IImportFileUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/import")
@AllArgsConstructor
public class ImportController {

    private final IImportFileUseCase importFileUseCase;

    @PostMapping
    public ResponseEntity<?> importFile(MultipartFile file) {
        importFileUseCase.importFile(file);
        return ResponseEntity.ok(200);
    }


}
