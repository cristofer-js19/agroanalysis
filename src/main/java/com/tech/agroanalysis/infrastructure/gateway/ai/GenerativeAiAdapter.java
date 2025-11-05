package com.tech.agroanalysis.infrastructure.gateway.ai;

import com.openai.client.OpenAIClient;
import com.openai.models.responses.ResponseCreateParams;
import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.application.port.out.GenerativeAiDataPort;
import com.tech.agroanalysis.domain.model.GenerativeAiAnalysis;
import com.tech.agroanalysis.domain.model.TimeSeriesProfile;
import com.tech.agroanalysis.domain.model.WeatherForecastProfile;
import com.tech.agroanalysis.infrastructure.gateway.ai.mapper.GenerativeAiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenerativeAiAdapter implements GenerativeAiDataPort {

    private final OpenAIClient aiClient;

    @Value("${ai.llm.model}")
    private String llmModel;

    @Cacheable("aiAnalysis")
    @Override
    public GenerativeAiAnalysis generateAnalysis(AgroAnalysisInput input, TimeSeriesProfile timeSeriesProfile,
                                                 WeatherForecastProfile weatherForecastProfile) throws Exception {
        var params = ResponseCreateParams.builder()
                .input(GenerativeAiMapper.toPromptFormat(input, timeSeriesProfile, weatherForecastProfile))
                .model(llmModel)
                .build();

        return GenerativeAiMapper.toDomain(input, aiClient.responses().create(params));
    }
}
