package com.tech.agroanalysis.application.port.out;

import com.tech.agroanalysis.domain.model.TimeSeriesProfile;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.dto.TimeSeriesRequest;

public interface TimeSeriesProfileDataPort {
    TimeSeriesProfile getTimeSeriesProfile(TimeSeriesRequest request);
}
