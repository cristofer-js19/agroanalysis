package com.tech.agroanalysis.infrastructure.gateway.weather;

import com.tech.agroanalysis.infrastructure.gateway.weather.dto.WeatherForecastResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Review: url, url value and request params
@FeignClient(name = "weatherForecastClient", url = "${}")
public interface WeatherForecastFeignClient {
    @GetMapping("")
    WeatherForecastResponse getWeatherForecast(@RequestParam("latitude") double latitude,
                                               @RequestParam("longitude") double longitude,
                                               @RequestParam("hourly") String hourly);
}
