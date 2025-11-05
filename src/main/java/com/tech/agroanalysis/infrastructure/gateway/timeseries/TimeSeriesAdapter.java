package com.tech.agroanalysis.infrastructure.gateway.timeseries;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.application.port.out.TimeSeriesProfileDataPort;
import com.tech.agroanalysis.domain.model.TimeSeriesProfile;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.dto.TimeSeriesResponse;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.mapper.TimeSeriesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimeSeriesAdapter implements TimeSeriesProfileDataPort {

    private final TimeSeriesFeignClient timeSeriesClient;

    @Cacheable("timeSeries")
    @Override
    public TimeSeriesProfile getTimeSeriesProfile(AgroAnalysisInput input) {
        TimeSeriesResponse response = timeSeriesClient.querySeries(TimeSeriesMapper.toApiRequest(input));
        return TimeSeriesMapper.toDomain(response);
    }
}
