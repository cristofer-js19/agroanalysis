package com.tech.agroanalysis.infrastructure.gateway.timeseries;

import com.tech.agroanalysis.infrastructure.gateway.timeseries.config.TimeSeriesFeignClientConfig;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.dto.TimeSeriesRequest;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.dto.TimeSeriesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "timeSeriesClient", url = "${http.satveg.base-url}",
        configuration = TimeSeriesFeignClientConfig.class)
public interface TimeSeriesFeignClient {
    @PostMapping(value = "${http.satveg.uri}", consumes = "application/json")
    TimeSeriesResponse querySeries(@RequestBody TimeSeriesRequest request);
}
