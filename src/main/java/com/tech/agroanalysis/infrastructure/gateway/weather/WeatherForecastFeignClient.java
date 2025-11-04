package com.tech.agroanalysis.infrastructure.gateway.weather;

import com.tech.agroanalysis.infrastructure.gateway.weather.dto.WeatherForecastResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weatherForecastClient", url = "${http.openmeteo.base-url}")
public interface WeatherForecastFeignClient {
    @GetMapping("${http.openmeteo.host}")
    WeatherForecastResponse getWeatherForecast(@RequestParam("latitude") double latitude,
                                               @RequestParam("longitude") double longitude,
                                               @RequestParam("hourly") String hourly);
}
