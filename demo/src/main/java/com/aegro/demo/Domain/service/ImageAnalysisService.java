package com.aegro.demo.Domain.service;

import com.aegro.demo.Domain.EntitiesModel.FileInfoModel;
import com.aegro.demo.Domain.EntitiesModel.RomaneioModel;
import com.aegro.demo.Domain.EntitiesModel.FormatResponse;
import com.aegro.demo.InterfaceAdpter.Repository.Repository.RomaneioRepository;
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

    public List<FileInfoModel> analyzeImages(List<MultipartFile> files, String userEmail) throws Exception{

        List<FileInfoModel> allAnalysisResult = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    FileInfoModel analysisResult = analyzeSingleImage(file, userEmail);
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

    private FileInfoModel analyzeSingleImage(MultipartFile file, String userEmail) throws IOException {

        String geminiResponse = geminiApiService.processImage(file.getBytes());
        FormatResponse formatResponse = new FormatResponse(geminiResponse);
        JSONObject jsonResponse = formatResponse.formatToJson();
        FileInfoModel fileInfoResponse = formatResponse.formatToFileInfo();
        System.out.println(fileInfoResponse.toString());

        RomaneioModel romaneioModel = new RomaneioModel();
        romaneioModel.setUserEmail(userEmail);
        romaneioModel.setFileName(file.getOriginalFilename());
        romaneioModel.setFileInfoModel(fileInfoResponse);
        romaneioRepository.save(romaneioModel);

        return fileInfoResponse;
    }

}
