package com.aegro.demo.Domain.service;

import com.aegro.demo.Domain.EntitiesModel.FileInfoModel;
import com.aegro.demo.Domain.EntitiesModel.RomaneioModel;
import com.aegro.demo.Domain.EntitiesModel.FormatResponse;
import com.aegro.demo.InterfaceAdpter.Repository.Entities.Romaneio;
import com.aegro.demo.InterfaceAdpter.Repository.Repository.RomaneioRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageAnalysisService extends Throwable {

    @Autowired
    private GeminiService geminiApiService;

    @Autowired
    private RomaneioRepository romaneioRepository;

    public List<RomaneioModel> analyzeImages(List<MultipartFile> files, String userEmail) throws Exception{

        List<RomaneioModel> allAnalysisResult = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    RomaneioModel analysisResult = analyzeSingleImage(file, userEmail);
                    allAnalysisResult.add(analysisResult);
                } catch (Exception e) {
                    System.err.println("Error parsing file " + file.getOriginalFilename() + ": " + e.getMessage());
                }
            }
        }

        return allAnalysisResult;
    }

    private RomaneioModel analyzeSingleImage(MultipartFile file, String userEmail) throws IOException {

        String geminiResponse = geminiApiService.processImage(file.getBytes());
        FormatResponse formatResponse = new FormatResponse(geminiResponse);
        FileInfoModel fileInfoResponse = formatResponse.formatToFileInfo();

        RomaneioModel romaneioModel = new RomaneioModel();
        romaneioModel.setUserEmail(userEmail);
        romaneioModel.setFileName(file.getOriginalFilename());
        romaneioModel.setFileInfoModel(fileInfoResponse);

        romaneioRepository.save(romaneioModel);

        return romaneioModel;
    }

    public List<RomaneioModel> findAll() {
        return romaneioRepository.findAll();

    }

    public List<RomaneioModel> findAllByUserEmail(String userEmail) {
        return romaneioRepository.findAllByUserEmail(userEmail);
    }
}
