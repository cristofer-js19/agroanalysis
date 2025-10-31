package com.tech.agroanalysis.infrastructure.gateway.timeseries.config;

import feign.RequestInterceptor;

public class TimeSeriesConfig { //TODO: Review class name, method param and headers
    public RequestInterceptor timeSeriesHeaderInterceptor(String apiKey) {
        return template -> {
            template.header("X-API-KEY", apiKey);
            template.header("Content-Type", "application/json");
        };
    }
}
