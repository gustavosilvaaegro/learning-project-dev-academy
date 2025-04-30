package com.aegro.demo.controller;

import com.aegro.demo.model.FileInfo;
import com.aegro.demo.service.ImageAnalysisService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
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
    private ImageAnalysisService imageAnalysisService;

    @PostMapping("/upload")
    public ResponseEntity<List<FileInfo>> handleFileUpload(@RequestParam("files")List<MultipartFile> files,
                                                         @RequestParam("userEmail") String userEmail) {
        try {
            List<FileInfo> analysisResults = imageAnalysisService.analyzeImages(files, userEmail);
            return ResponseEntity.ok(analysisResults);
        } catch (Exception e) {
            //return ResponseEntity.badRequest().body(List.of("Error processing files: " + e.getMessage()));
            return ResponseEntity.badRequest().body(List.of());
        }
    }
}
