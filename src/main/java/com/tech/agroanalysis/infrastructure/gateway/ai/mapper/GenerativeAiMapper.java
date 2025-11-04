package com.tech.agroanalysis.infrastructure.gateway.ai.mapper;

import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseOutputMessage;
import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.application.dto.AgroAnalysisOutput;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.dto.TimeSeriesResponse;
import com.tech.agroanalysis.infrastructure.gateway.weather.dto.WeatherForecastResponse;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public class GenerativeAiMapper {
    public static String toPromptFormat(AgroAnalysisInput input) {
        return "";
    }

    public AgroAnalysisOutput toDomain(Response response) throws Exception {
        Optional<ResponseOutputMessage> responseMessage = response.output().getLast().message();
        if (responseMessage.isPresent()) {
            return AgroAnalysisOutput.builder()
                    .analysisResult(responseMessage.get().content().getFirst().asOutputText().text())
                    .build();
        }

        throw new Exception("Invalid response"); //TODO: Personalize exception
    }

    private String buildPrompt( //TODO: Check
            String profileType,
            String satellite,
            int preFilter,
            String filter,
            int filterParam,
            double latitude,
            double longitude,
            String plantType,
            String requisitorName,
            TimeSeriesResponse timeSeriesResponse,
            WeatherForecastResponse weatherForecastResponse
    ) {

        // Conversão das listas para texto simples (limite opcional para evitar prompts muito grandes)
        String listaSerie = joinList(timeSeriesResponse.timeSeriesList(), ", ");
        String listaDatas = joinList(timeSeriesResponse.dateList(), ", ");

        String horarios = joinList(weatherForecastResponse.hourly().timeHistoryList(), ", ");
        String temperaturas = joinList(weatherForecastResponse.hourly().temperatureHistoryList(), ", ");
        String probPrecipitacao = joinList(weatherForecastResponse.hourly().precipitationProbabilityList(), ", ");
        String chuvas = joinList(weatherForecastResponse.hourly().rainfallMilimeterList(), ", ");

        return """
                Você é um especialista em geotecnologia agrícola e análise ambiental. 
                Com base nos dados a seguir, avalie as condições de SOLO e CLIMA para o cultivo informado.

                ### DADOS SATVEG (Índices Vegetativos)
                Os dados a seguir são provenientes da API SATVeg da Embrapa, que utiliza imagens MODIS dos satélites Terra e Aqua.
                Eles representam a série temporal do índice vegetativo (%s) no ponto de coordenadas (%.5f, %.5f).
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
                Os dados a seguir foram obtidos a partir da API Open-Meteo para as mesmas coordenadas (%.5f, %.5f).

                Informações gerais:
                - Timezone: %s
                - Elevação: %.2f m

                Séries horárias:
                - Datas/Horários: %s
                - Temperaturas (°C): %s
                - Probabilidade de precipitação (%%): %s
                - Chuva (mm): %s

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
                profileType, latitude, longitude,
                satellite, preFilter, filter, filterParam,
                listaSerie, listaDatas,
                latitude, longitude,
                weatherForecastResponse.timezone(), weatherForecastResponse.elevation(),
                horarios, temperaturas, probPrecipitacao, chuvas,
                plantType, requisitorName
        );
    }

    private String joinList(List<?> list, String delimiter) {
        if (list == null || list.isEmpty()) {
            return "(sem dados)";
        }
        StringJoiner joiner = new StringJoiner(delimiter);
        list.forEach(item -> joiner.add(String.valueOf(item)));
        return joiner.toString();
    }
}
