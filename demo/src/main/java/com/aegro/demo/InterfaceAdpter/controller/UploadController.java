package com.aegro.demo.InterfaceAdpter.controller;

import com.aegro.demo.Application.Dtos.FileInfoDTO;
import com.aegro.demo.Application.Dtos.RomaneioDTO;
import com.aegro.demo.Application.UseCases.RomaneioUC;
import com.aegro.demo.Application.UseCases.UploadUC;
import com.aegro.demo.InterfaceAdpter.controller.docs.UploadControllerDocs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/images")
@Tag(name = "Upload", description = "Upload and manage files")
public class UploadController implements UploadControllerDocs {

    @Autowired
    private UploadUC imageAnalysisService;

    @Autowired
    private RomaneioUC romaneioUC;


    @PostMapping("/upload")
    @Override
    public ResponseEntity<List<RomaneioDTO>> handleFileUpload(@RequestParam("files") List<MultipartFile> files,
                                                              @RequestParam("userEmail") String userEmail) {
        try {
            List<RomaneioDTO> analysisResults = imageAnalysisService.execute(files, userEmail);
            return ResponseEntity.ok(analysisResults);
        } catch (Exception e) {
            //return ResponseEntity.badRequest().body(List.of("Error processing files: " + e.getMessage()));
            return ResponseEntity.badRequest().body(List.of());
        }
    }

    @GetMapping("/files")
    @Override
    public ResponseEntity<List<RomaneioDTO>> findAllFiles() {
        List<RomaneioDTO> allFiles = romaneioUC.findAll();
        return ResponseEntity.ok(allFiles);
    }

    @GetMapping("/files/email")
    @Override
    public ResponseEntity<List<RomaneioDTO>> findeAllByUserEmail(@RequestParam("userEmail") String userEmail) {
        List<RomaneioDTO> allFilesByUserEmail = romaneioUC.findAllByUserEmail(userEmail);
        if (allFilesByUserEmail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.ok(allFilesByUserEmail);
    }
}
