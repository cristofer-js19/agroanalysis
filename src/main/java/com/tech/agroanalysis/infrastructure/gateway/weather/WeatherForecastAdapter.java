package com.tech.agroanalysis.infrastructure.gateway.weather;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.application.port.out.WeatherForecastProfileDataPort;
import com.tech.agroanalysis.domain.model.WeatherForecastProfile;
import com.tech.agroanalysis.infrastructure.gateway.weather.dto.WeatherForecastResponse;
import com.tech.agroanalysis.infrastructure.gateway.weather.mapper.WeatherForecastMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherForecastAdapter implements WeatherForecastProfileDataPort {

    private final WeatherForecastFeignClient weatherForecastClient;

    private static final String HOURLY = "temperature_2m,precipitation_probability";

    @Cacheable("weather")
    @Override
    public WeatherForecastProfile getWeatherForecast(AgroAnalysisInput input) {
        WeatherForecastResponse response =
                weatherForecastClient.getWeatherForecast(
                        Double.parseDouble(input.latitude()), Double.parseDouble(input.longitude()), HOURLY);
        return WeatherForecastMapper.toDomain(response);
    }
}
