package com.tech.agroanalysis.infrastructure.gateway.ai.util;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.domain.model.GenerativeAiAnalysis;

public class GenerativeAiResponseParser {

    private static final String SOIL_ANALYSIS_MARKER = "===ANALISE_SOLO===";
    private static final String WEATHER_ANALYSIS_MARKER = "===ANALISE_CLIMA===";

    public static GenerativeAiAnalysis parse(AgroAnalysisInput input, String llmResponse) throws Exception {
        if (llmResponse == null || llmResponse.isBlank()) {
            throw new Exception("Failure while parsing AI analysis response"); //TODO: Personalize exception
        }

        String text = llmResponse.trim();

        int idxSoil = text.indexOf(SOIL_ANALYSIS_MARKER);
        int idxClimate = text.indexOf(WEATHER_ANALYSIS_MARKER);

        String soil = "";
        String climate = "";

        if (idxSoil >= 0 && idxClimate > idxSoil) {
            soil = text.substring(idxSoil + SOIL_ANALYSIS_MARKER.length(), idxClimate).trim();
        }

        if (idxClimate >= 0) {
            climate = text.substring(idxClimate + WEATHER_ANALYSIS_MARKER.length()).trim();
        }

        return GenerativeAiAnalysis.builder()
                .plantType(input.plantType())
                .weatherAnalysis(clean(climate))
                .soilAnalysis(clean(soil))
                .build();
    }

    private static String clean(String text) {
        if (text == null) return "";
        return text.replaceAll("\\*\\*", "").replaceAll(" ", "").trim();
    }
}
