package com.tech.agroanalysis.infrastructure.gateway.ai.util;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.domain.model.TimeSeriesProfile;
import com.tech.agroanalysis.domain.model.WeatherForecastProfile;

import java.util.List;
import java.util.StringJoiner;

public class GenerativeAiPromptBuilder {
    public static String buildPrompt(AgroAnalysisInput input, TimeSeriesProfile timeSeriesProfile,
                                      WeatherForecastProfile weatherForecastProfile) {

        // Conversion of list in plain text to avoid overly long prompts
        String timeSeriesListStr = joinList(timeSeriesProfile.timeSeriesList());
        String dateListStr = joinList(timeSeriesProfile.dateList());

        String timeHistoryListStr = joinList(weatherForecastProfile.hourly().timeHistoryList());
        String temperatureListStr = joinList(weatherForecastProfile.hourly().temperatureHistoryList());
        String precipitationProbabilityListStr = joinList(weatherForecastProfile.hourly().precipitationProbabilityHistoryList());

        return """
                Você é um especialista em geotecnologia agrícola e análise ambiental. 
                Com base nos dados a seguir, avalie as condições de SOLO e CLIMA para o cultivo informado.

                ### DADOS SATVEG (Índices Vegetativos)
                Os dados a seguir são provenientes da API SATVeg da Embrapa, que utiliza imagens MODIS dos satélites Terra e Aqua.
                Eles representam a série temporal do índice vegetativo (%s) no ponto de coordenadas (%s, %s).
                Esses índices (NDVI/EVI) refletem o vigor e a biomassa verde da vegetação, permitindo inferir a condição do solo e da cobertura vegetal.

                Parâmetros da consulta:
                - Satélite: %s
                - Pré-filtro: %d (onde 0=sem filtragem, 1=nodata, 2=nuvem, 3=nuvem/nodata)
                - Filtro: %s
                - Parâmetro do filtro: %d

                Série temporal:
                - Valores NDVI/EVI: %s
                - Datas correspondentes: %s

                ### DADOS METEOROLÓGICOS (Open-Meteo)
                Os dados a seguir foram obtidos a partir da API Open-Meteo para as mesmas coordenadas (%s, %s).

                Informações gerais:
                - Timezone: %s
                - Elevação: %.2f m

                Séries horárias:
                - Datas/Horários: %s
                - Temperaturas (°C): %s
                - Probabilidade de precipitação (%%): %s

                ### CULTURA AGRÍCOLA
                A cultura agrícola a ser analisada é: **%s**.

                ### ORIENTAÇÃO DE ANÁLISE
                Com base nos dados acima:
                1. Analise o comportamento da vegetação (índices NDVI/EVI) ao longo do tempo, considerando o vigor vegetal e possíveis períodos de estresse hídrico ou degradação do solo. 
                2. Avalie as condições climáticas recentes (temperatura, precipitação, probabilidade de chuva) e como elas influenciam a viabilidade da cultura informada.
                3. Forneça recomendações práticas, indicando se as condições são favoráveis, neutras ou desfavoráveis à cultura.
                4. A resposta deve estar dividida em duas partes: uma para análise do SOLO e outra para CLIMA.
                5. Utilize uma linguagem técnica, mas compreensível, e conclua com uma saudação personalizada ao solicitante.

                A estrutura da resposta deve seguir exatamente o formato abaixo:

                ===ANALISE_SOLO===
                [Escreva aqui a análise detalhada do solo, com base nos índices NDVI/EVI e seu comportamento temporal. Mencione vigor vegetal, cobertura, indícios de degradação ou potencial produtivo do solo.]
                ===ANALISE_CLIMA===
                [Escreva aqui a análise detalhada do clima, considerando temperatura, precipitação e condições meteorológicas recentes, indicando se são adequadas à cultura informada.]
                ---
                Assine de forma cordial mencionando o nome do solicitante: **%s**.
                """.formatted(
                timeSeriesProfile.profileType(), input.latitude(), input.longitude(),
                timeSeriesProfile.satellite(), timeSeriesProfile.preFilter(), timeSeriesProfile.filter(), timeSeriesProfile.filterParam(),
                timeSeriesListStr, dateListStr,
                input.latitude(), input.longitude(),
                weatherForecastProfile.timezone(), weatherForecastProfile.elevation(),
                timeHistoryListStr, temperatureListStr, precipitationProbabilityListStr,
                input.plantType(), input.username()
        );
    }

    private static String joinList(List<?> list) {
        if (list == null || list.isEmpty()) {
            return "(sem dados)";
        }
        StringJoiner joiner = new StringJoiner(", ");
        list.forEach(item -> joiner.add(String.valueOf(item)));
        return joiner.toString();
    }
}
