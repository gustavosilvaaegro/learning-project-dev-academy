package com.aegro.demo.Domain.EntitiesModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormatResponseTest {

    private String geminiResponse = "{\n" +
            "  \"candidates\": [\n" +
            "    {\n" +
            "      \"content\": {\n" +
            "        \"parts\": [\n" +
            "          {\n" +
            "            \"text\": \"```json\\n{\\n  \\\"destino_armazem\\\": \\\"Armazem - Silo GL\\\",\\n  \\\"produto\\\": \\\"MILHO GRAOS\\\",\\n  \\\"data\\\": \\\"07/08/23\\\",\\n  \\\"produtor\\\": \\\"0\\\",\\n  \\\"peso_bruto_kg\\\": 82920.0,\\n  \\\"tara_kg\\\": 25860.0,\\n  \\\"peso_liquido_kg\\\": 51726.0,\\n  \\\"descontos_impureza_percentual\\\": 0.6,\\n  \\\"descontos_impureza_quantidade_kg\\\": 342.3,\\n  \\\"descontos_umidade_percentual\\\": 8.8,\\n  \\\"descontos_umidade_quantidade_kg\\\": 4991.0,\\n  \\\"total_descontos_kg\\\": 5333.3,\\n  \\\"numero_romaneio\\\": \\\"30.377\\\",\\n  \\\"placa_veiculo\\\": \\\"OOM8B01\\\",\\n  \\\"nome_motorista\\\": \\\"JAKSON FERNANDO ZOTT\\\"\\n}\\n```\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"role\": \"model\"\n" +
            "      },\n" +
            "      \"finishReason\": \"STOP\",\n" +
            "      \"avgLogprobs\": -0.0044892577617340664\n" +
            "    }\n" +
            "  ],\n" +
            "  \"usageMetadata\": {\n" +
            "    \"promptTokenCount\": 2023,\n" +
            "    \"candidatesTokenCount\": 263,\n" +
            "    \"totalTokenCount\": 2286,\n" +
            "    \"promptTokensDetails\": [\n" +
            "      {\n" +
            "        \"modality\": \"TEXT\",\n" +
            "        \"tokenCount\": 217\n" +
            "      },\n" +
            "      {\n" +
            "        \"modality\": \"IMAGE\",\n" +
            "        \"tokenCount\": 1806\n" +
            "      }\n" +
            "    ],\n" +
            "    \"candidatesTokensDetails\": [\n" +
            "      {\n" +
            "        \"modality\": \"TEXT\",\n" +
            "        \"tokenCount\": 263\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"modelVersion\": \"gemini-2.0-flash\"\n" +
            "}";


    @Test
    void formatToFileInfo() throws JsonProcessingException {
        FileInfoModel fileInfoExpected = new FileInfoModel("07/08/23", "342.3",
                "8.8", "OOM8B01", "30.377", "82920.0",
                "25860.0", "JAKSON FERNANDO ZOTT", "MILHO GRAOS", "0",
                "0.6", "51726.0", "5333.3",
                "Armazem - Silo GL", "4991.0");

        FormatResponse formatResponse = new FormatResponse(geminiResponse);
        FileInfoModel fileInfo = formatResponse.formatToFileInfo();

        assertEquals(fileInfoExpected, fileInfo);
    }

}