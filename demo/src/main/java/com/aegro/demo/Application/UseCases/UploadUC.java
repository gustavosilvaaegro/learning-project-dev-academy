package com.aegro.demo.Application.UseCases;

import com.aegro.demo.Application.Dtos.FileInfoDTO;
import com.aegro.demo.Application.Mappers.FileInfoMapper;
import com.aegro.demo.Domain.service.ImageAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class UploadUC {

    @Autowired
    private ImageAnalysisService imageAnalysisService;

    public List<FileInfoDTO> execute(List<MultipartFile> files, String userEmail) throws Exception {
        return imageAnalysisService.analyzeImages(files, userEmail)
                .stream()
                .map(FileInfoMapper::toDTO)
                .toList();
    }
}
