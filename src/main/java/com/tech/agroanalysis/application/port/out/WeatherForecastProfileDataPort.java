package com.tech.agroanalysis.application.port.out;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.domain.model.WeatherForecastProfile;

public interface WeatherForecastProfileDataPort {
    WeatherForecastProfile getWeatherForecast(AgroAnalysisInput input);
}
