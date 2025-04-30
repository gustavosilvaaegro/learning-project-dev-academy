package com.aegro.demo.InterfaceAdpter.controller;

import com.aegro.demo.Application.Dtos.FileInfoDTO;
import com.aegro.demo.Application.UseCases.UploadUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class UploadController {

    @Autowired
    private UploadUC imageAnalysisService;

    @PostMapping("/upload")
    public ResponseEntity<List<FileInfoDTO>> handleFileUpload(@RequestParam("files")List<MultipartFile> files,
                                                         @RequestParam("userEmail") String userEmail) {
        try {
            List<FileInfoDTO> analysisResults = imageAnalysisService.execute(files, userEmail);
            return ResponseEntity.ok(analysisResults);
        } catch (Exception e) {
            //return ResponseEntity.badRequest().body(List.of("Error processing files: " + e.getMessage()));
            return ResponseEntity.badRequest().body(List.of());
        }
    }
}
