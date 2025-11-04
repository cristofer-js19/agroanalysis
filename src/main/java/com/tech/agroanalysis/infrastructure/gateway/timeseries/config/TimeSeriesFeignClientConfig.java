package com.tech.agroanalysis.infrastructure.gateway.timeseries.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class TimeSeriesFeignClientConfig {

    @Value("${http.satveg.api-key}")
    private String apiKey;

    private static final String AUTH_HEADER = "Authorization";

    @Bean
    public RequestInterceptor timeSeriesHeaderInterceptor() {
        return template -> {
            template.header(AUTH_HEADER, String.format("Bearer %s", apiKey));
            template.header("Content-Type", "application/json");
        };
    }
}
