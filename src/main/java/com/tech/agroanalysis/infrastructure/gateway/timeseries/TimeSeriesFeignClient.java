package com.tech.agroanalysis.infrastructure.gateway.timeseries;

import com.tech.agroanalysis.infrastructure.gateway.timeseries.config.TimeSeriesConfig;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.dto.TimeSeriesRequest;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.dto.TimeSeriesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//Review: url and url value
@FeignClient(name = "timeSeriesClient", url = "${}", configuration = TimeSeriesConfig.class)
public interface TimeSeriesFeignClient {
    @PostMapping(value = "/", consumes = "application/json")
    TimeSeriesResponse querySeries(@RequestBody TimeSeriesRequest request);
}
