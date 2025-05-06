package com.aegro.demo.Domain.EntitiesModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class FormatResponse extends Throwable{

    private String response;

    public FormatResponse(String response) {
        this.response = response;
    }

    public JSONObject formatToJson() {
        String text = this.separateResponse();
        return new JSONObject(text);
    }

    public FileInfoModel formatToFileInfo() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String text = this.separateResponse();
        //System.out.println("Conteúdo do campo 'text':\n" + text);
        return objectMapper.readValue(text, FileInfoModel.class);
    }

    private String separateResponse() {
        JSONObject jsonObject = new JSONObject(this.response);
        JSONArray candidates = jsonObject.getJSONArray("candidates");
        JSONArray parts;
        String text = "";

        parts = separateParts(candidates);
        text = separateFirstPart(parts);

        return text;
    }

    private JSONArray separateParts(JSONArray candidates) {
        if (!candidates.isEmpty()) {
            JSONObject firstCandidate = candidates.getJSONObject(0);
            JSONObject content = firstCandidate.getJSONObject("content");
            return content.getJSONArray("parts");
        }

        return new JSONArray();
    }

    private String separateFirstPart(JSONArray parts) {

        String text = "";

        if (!parts.isEmpty()) {
            JSONObject firstPart = parts.getJSONObject(0);
            text = firstPart.getString("text");
            text = text.replace("```json", "").replace("```", "").replace("\n", "");
        }

        return text;
    }



}
