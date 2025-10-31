package com.tech.agroanalysis.application.usecase;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.application.dto.AgroAnalysisOutput;
import com.tech.agroanalysis.application.port.in.GenerativeAiAnalysisUseCase;
import com.tech.agroanalysis.application.port.out.TimeSeriesProfileDataPort;
import com.tech.agroanalysis.application.port.out.WeatherForecastProfileDataPort;
import com.tech.agroanalysis.domain.model.TimeSeriesProfile;
import com.tech.agroanalysis.domain.model.WeatherForecastProfile;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.mapper.TimeSeriesMapper;
import com.tech.agroanalysis.infrastructure.gateway.weather.mapper.WeatherForecastMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenerativeAiAnalysisUseCaseImpl implements GenerativeAiAnalysisUseCase {

    private final TimeSeriesProfileDataPort timeSeriesProfileDataPort;
    private final WeatherForecastProfileDataPort weatherForecastProfileDataPort;

    @Override
    public AgroAnalysisOutput execute(AgroAnalysisInput input) {
        TimeSeriesProfile timeSeriesProfile =
                timeSeriesProfileDataPort.getTimeSeriesProfile(TimeSeriesMapper.toApiRequest(input));

        WeatherForecastProfile weatherForecastProfile =
                weatherForecastProfileDataPort.getWeatherForecast(WeatherForecastMapper.toApiRequest(input));

        //TODO: Call AI and return result
        return null;
    }
}
