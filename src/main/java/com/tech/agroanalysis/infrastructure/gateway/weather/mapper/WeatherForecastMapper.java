package com.tech.agroanalysis.infrastructure.gateway.weather.mapper;

import com.tech.agroanalysis.domain.model.WeatherForecastProfile;
import com.tech.agroanalysis.infrastructure.entrypoint.dto.AgroAnalysisRequest;
import com.tech.agroanalysis.infrastructure.gateway.weather.dto.WeatherForecastRequest;
import com.tech.agroanalysis.infrastructure.gateway.weather.dto.WeatherForecastResponse;

public class WeatherForecastMapper {
    public static WeatherForecastProfile toDomain(WeatherForecastResponse response) {
        return WeatherForecastProfile.builder()
                .timeHistoryList(response.hourly().timeHistoryList())
                .temperatureHistoryList(response.hourly().temperatureHistoryList())
                .precipitationProbabilityHistoryList(response.hourly().precipitationProbabilityList())
                .rainfallMilimetersHistoryList(response.hourly().rainfallMilimeterList())
                .build();
    }

    public static WeatherForecastRequest toApiRequest(AgroAnalysisRequest request) {
        return WeatherForecastRequest.builder()
                .latitude(Integer.parseInt(request.latitude()))
                .longitude(Integer.parseInt(request.longitude()))
                .hourly("temperature_2m")
                .pastDays(7)
                .build();
    }
}
