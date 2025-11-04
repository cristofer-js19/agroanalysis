package com.tech.agroanalysis.infrastructure.gateway.ai.mapper;

import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseOutputMessage;
import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.domain.model.GenerativeAiAnalysis;
import com.tech.agroanalysis.domain.model.TimeSeriesProfile;
import com.tech.agroanalysis.domain.model.WeatherForecastProfile;
import com.tech.agroanalysis.infrastructure.gateway.ai.util.GenerativeAiResponseParser;

import java.util.Optional;

import static com.tech.agroanalysis.infrastructure.gateway.ai.util.GenerativeAiPromptBuilder.buildPrompt;

public class GenerativeAiMapper {
    public static String toPromptFormat(AgroAnalysisInput input, TimeSeriesProfile timeSeriesProfile,
                                        WeatherForecastProfile weatherForecastProfile) {
        return buildPrompt(input, timeSeriesProfile, weatherForecastProfile);
    }

    public static GenerativeAiAnalysis toDomain(Response response) throws Exception {
        Optional<ResponseOutputMessage> responseMessage = response.output().getLast().message();
        if (responseMessage.isPresent()) {
            return GenerativeAiResponseParser.parse(responseMessage.get().content().getFirst().asOutputText().text());
        }

        throw new Exception("Failure when mapping AI analysis response"); //TODO: Personalize exception
    }
}
