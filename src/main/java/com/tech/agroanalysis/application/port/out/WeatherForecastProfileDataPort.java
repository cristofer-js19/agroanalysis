package com.tech.agroanalysis.application.port.out;

import com.tech.agroanalysis.domain.model.WeatherForecastProfile;
import com.tech.agroanalysis.infrastructure.gateway.weather.dto.WeatherForecastRequest;

public interface WeatherForecastProfileDataPort {
    WeatherForecastProfile getWeatherForecast(WeatherForecastRequest request);
}
