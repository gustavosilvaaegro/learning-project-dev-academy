package com.aegro.demo.model;

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
        String text = this.formatString();

        return new JSONObject(text);
    }

    public FileInfo formatToFileInfo() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String text = this.formatString();

        return objectMapper.readValue(text, FileInfo.class);
    }

    private String formatString() {
        JSONObject jsonObject = new JSONObject(this.response);
        JSONArray candidates = jsonObject.getJSONArray("candidates");
        String text = "";


        if (candidates.length() > 0) {
            JSONObject firstCandidate = candidates.getJSONObject(0);
            JSONObject content = firstCandidate.getJSONObject("content");
            JSONArray parts = content.getJSONArray("parts");


            if (parts.length() > 0) {
                JSONObject firstPart = parts.getJSONObject(0);
                text = firstPart.getString("text");
                text = text.replace("```json", "").replace("```", "").replace("\n", "").replace(" ", "");
                System.out.println("Conteúdo do campo 'text':\n" + text);
            }
        }

        return text;
    }



}
