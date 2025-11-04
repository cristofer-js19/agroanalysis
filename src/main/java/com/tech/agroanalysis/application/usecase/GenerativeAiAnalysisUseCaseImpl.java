package com.tech.agroanalysis.application.usecase;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.application.dto.AgroAnalysisOutput;
import com.tech.agroanalysis.application.port.in.GenerativeAiAnalysisUseCase;
import com.tech.agroanalysis.application.port.out.GenerativeAiDataPort;
import com.tech.agroanalysis.application.port.out.TimeSeriesProfileDataPort;
import com.tech.agroanalysis.application.port.out.WeatherForecastProfileDataPort;
import com.tech.agroanalysis.domain.model.GenerativeAiAnalysis;
import com.tech.agroanalysis.domain.model.TimeSeriesProfile;
import com.tech.agroanalysis.domain.model.WeatherForecastProfile;
import com.tech.agroanalysis.infrastructure.entrypoint.http.mapper.AgroAnalysisMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenerativeAiAnalysisUseCaseImpl implements GenerativeAiAnalysisUseCase {

    private final TimeSeriesProfileDataPort timeSeriesProfileDataPort;
    private final WeatherForecastProfileDataPort weatherForecastProfileDataPort;
    private final GenerativeAiDataPort generativeAiDataPort;

    @Override
    public AgroAnalysisOutput execute(AgroAnalysisInput input) throws Exception {
        TimeSeriesProfile timeSeriesProfile = timeSeriesProfileDataPort.getTimeSeriesProfile(input);
        WeatherForecastProfile weatherForecastProfile = weatherForecastProfileDataPort.getWeatherForecast(input);
        GenerativeAiAnalysis generativeAiAnalysis = generativeAiDataPort.generateAnalysis(input, timeSeriesProfile,
                weatherForecastProfile);

        return AgroAnalysisMapper.toUseCaseOutput(generativeAiAnalysis);
    }
}
