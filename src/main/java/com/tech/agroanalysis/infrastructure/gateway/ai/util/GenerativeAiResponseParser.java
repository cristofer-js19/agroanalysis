package com.tech.agroanalysis.infrastructure.gateway.ai.util;

import com.tech.agroanalysis.domain.model.GenerativeAiAnalysis;

public class GenerativeAiResponseParser {

    private static final String SOIL_ANALYSIS_MARKER = "===ANALISE_SOLO===";
    private static final String WEATHER_ANALYSIS_MARKER = "===ANALISE_CLIMA===";
    private static final String SIGNATURE_MARKER = "---";

    public static GenerativeAiAnalysis parse(String llmResponse) throws Exception {
        if (llmResponse == null || llmResponse.isBlank()) {
            throw new Exception("Failure while parsing AI analysis response"); //TODO: Personalize exception
        }

        String text = llmResponse.trim();

        int idxSoil = text.indexOf(SOIL_ANALYSIS_MARKER);
        int idxClimate = text.indexOf(WEATHER_ANALYSIS_MARKER);
        int idxSignature = text.indexOf(SIGNATURE_MARKER);

        String soil = "";
        String climate = "";
        String signature = "";

        if (idxSoil >= 0 && idxClimate > idxSoil) {
            soil = text.substring(idxSoil + SOIL_ANALYSIS_MARKER.length(), idxClimate).trim();
        }

        if (idxClimate >= 0) {
            if (idxSignature > idxClimate) {
                climate = text.substring(idxClimate + WEATHER_ANALYSIS_MARKER.length(), idxSignature).trim();
            } else {
                climate = text.substring(idxClimate + WEATHER_ANALYSIS_MARKER.length()).trim();
            }
        }

        if (idxSignature >= 0) {
            signature = text.substring(idxSignature).trim();
        }

        return GenerativeAiAnalysis.builder()
                .soilAnalysis(clean(soil))
                .weatherAnalysis(clean(climate))
                .signature(clean(signature))
                .build();
    }

    private static String clean(String text) {
        if (text == null) return "";
        return text.replaceAll("\\*\\*", "").trim();
    }
}
