package com.aegro.demo.Domain.service;

import com.aegro.demo.InterfaceAdpter.Entities.FileInfo;
import com.aegro.demo.Domain.EntitiesModel.FormatResponse;
import com.aegro.demo.InterfaceAdpter.Entities.Romaneio;
import com.aegro.demo.Domain.IRepository.RomaneioRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageAnalysisService extends Throwable {

    @Autowired
    private GeminiService geminiApiService;

    @Autowired
    private RomaneioRepository romaneioRepository;

    public List<FileInfo> analyzeImages(List<MultipartFile> files, String userEmail) throws Exception{

        List<FileInfo> allAnalysisResult = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    FileInfo analysisResult = analyzeSingleImage(file, userEmail);
                    allAnalysisResult.add(analysisResult);
                } catch (Exception e) {
                    System.err.println("Error parsing file " + file.getOriginalFilename() + ": " + e.getMessage());
                    //allAnalysisResult.add("Error parsing " + file.getOriginalFilename() + " file: " + e.getMessage());
                }
            } //else {
               //allAnalysisResult.add(file.getOriginalFilename() + " file is empty.");
            //}
        }

        return allAnalysisResult;
    }

    private FileInfo analyzeSingleImage(MultipartFile file, String userEmail) throws IOException {

        String geminiResponse = geminiApiService.processImage(file.getBytes());
        FormatResponse formatResponse = new FormatResponse(geminiResponse);
        JSONObject jsonResponse = formatResponse.formatToJson();
        FileInfo fileInfoResponse = formatResponse.formatToFileInfo();


        Romaneio romaneio = new Romaneio();
        romaneio.setUserEmail(userEmail);
        romaneio.setFileName(file.getOriginalFilename());
        romaneio.setFileInfo(fileInfoResponse);
        romaneioRepository.save(romaneio);

        return fileInfoResponse;
    }

}
