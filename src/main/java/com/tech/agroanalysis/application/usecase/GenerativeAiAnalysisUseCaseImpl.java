package com.tech.agroanalysis.application.usecase;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.application.dto.AgroAnalysisOutput;
import com.tech.agroanalysis.application.port.in.GenerativeAiAnalysisUseCase;
import com.tech.agroanalysis.application.port.out.GenerativeAiDataPort;
import com.tech.agroanalysis.application.port.out.TimeSeriesProfileDataPort;
import com.tech.agroanalysis.application.port.out.WeatherForecastProfileDataPort;
import com.tech.agroanalysis.domain.model.TimeSeriesProfile;
import com.tech.agroanalysis.domain.model.WeatherForecastProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenerativeAiAnalysisUseCaseImpl implements GenerativeAiAnalysisUseCase {

    private final TimeSeriesProfileDataPort timeSeriesProfileDataPort;
    private final WeatherForecastProfileDataPort weatherForecastProfileDataPort;
    private final GenerativeAiDataPort generativeAiDataPort;

    @Override
    public AgroAnalysisOutput execute(AgroAnalysisInput input) {
        //TimeSeriesProfile timeSeriesProfile = timeSeriesProfileDataPort.getTimeSeriesProfile(input);
        //WeatherForecastProfile weatherForecastProfile = weatherForecastProfileDataPort.getWeatherForecast(input);
        String result = generativeAiDataPort.generateAnalysis();

        //TODO: Call AI and return result
        return null;
    }
}
