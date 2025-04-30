package com.aegro.demo.model;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeminiAPI extends Throwable {

    private final String apiKey;
    private final String apiURL;
    private final RestTemplate restTemplate;

    private final String prompt = "You are an expert data extraction specialist.\n" +
            "\n" +
            "You will use OCR to extract the data from the scale ticket provided in the image.\n" +
            "\n" +
            "Extract the following fields:\n" +
            "\n" +
            "{\n" +
            "  \"destino_armazem\": \"...\",\n" +
            "  \"produto\": \"...\",\n" +
            "  \"data\": \"...\",\n" +
            "  \"produtor\": \"...\",\n" +
            "  \"peso_bruto_kg\": ...,\n" +
            "  \"tara_kg\": ...,\n" +
            "  \"peso_liquido_kg\": ...,\n" +
            "  \"descontos_impureza_percentual\": ...,\n" +
            "  \"descontos_impureza_quantidade_kg\": ...,\n" +
            "  \"descontos_umidade_percentual\": ...,\n" +
            "  \"descontos_umidade_quantidade_kg\": ...,\n" +
            "  \"total_descontos_kg\": ...,\n" +
            "  \"numero_romaneio\": \"...\",\n" +
            "  \"placa_veiculo\": \"...\",\n" +
            "  \"nome_motorista\": \"...\"\n" +
            "}\n" +
            "\n" +
            "- The JSON schema must be followed during the extraction.\n" +
            "- The values must only include values found in the document.\n" +
            "- Generate null for missing entities.\n" +
            "- Return numeric values as numbers, not strings.\n";
    private final String escapedPrompt = prompt.replace("\n", "").replace("\"", "\\\"");

    public GeminiAPI() {
        //this.apiKey = System.getenv("GEMINI_API_KEY");
        this.apiKey = "AIzaSyDQaDcWERdclbyzXzdu8uiW3eXJu4wZYik";
        this.apiURL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;
        this.restTemplate = new RestTemplate();
        if (this.apiKey == null) {
            throw new IllegalArgumentException("GEMINI_API_KEY environment variable is not set.");
        }
    }

    public String analyzeImage(String base64Image) {
        try {
            String jsonInput = """
                {
                  "contents": [
                    {
                      "parts": [
                        {
                          "text": "%s"
                        },
                        {
                          "inlineData": {
                            "mimeType": "image/jpeg",
                            "data": "%s"
                          }
                        }
                      ]
                    }
                  ]
                }
                """.formatted(escapedPrompt, base64Image);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(jsonInput, headers);

            ResponseEntity<String> response = restTemplate.exchange(apiURL, HttpMethod.POST, entity, String.class);

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao chamar a API Gemini: " + e.getMessage(), e);
        }
    }

}
