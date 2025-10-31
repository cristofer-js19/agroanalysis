package com.tech.agroanalysis.infrastructure.gateway.timeseries.mapper;

import com.tech.agroanalysis.domain.model.TimeSeriesProfile;
import com.tech.agroanalysis.infrastructure.entrypoint.dto.AgroAnalysisRequest;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.dto.TimeSeriesRequest;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.dto.TimeSeriesResponse;

public class TimeSeriesMapper {
    public static TimeSeriesProfile toDomain(TimeSeriesResponse response) {
        return TimeSeriesProfile.builder()
                .timeSeriesList(response.timeSeriesList())
                .dateList(response.dateList())
                .build();
    }

    public static TimeSeriesRequest toApiRequest(AgroAnalysisRequest request) {
        return TimeSeriesRequest.builder()
                .profileType("ndvi")
                .satellite("comb")
                .preFilter(3)
                .filter("sav")
                .filterParam(4)
                .polygon(request.polygon())
                .build();
    }
}
