package com.tech.agroanalysis.infrastructure.gateway.timeseries.mapper;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.domain.model.TimeSeriesProfile;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.dto.TimeSeriesRequest;
import com.tech.agroanalysis.infrastructure.gateway.timeseries.dto.TimeSeriesResponse;

import static com.tech.agroanalysis.infrastructure.gateway.util.ListUtil.equalizeListDimension;

public class TimeSeriesMapper {

    private static final String PROFILE_TYPE = "ndvi";
    private static final String SATELLITE = "comb";
    private static final Integer PRE_FILTER = 3;
    private static final String FILTER = "sav";
    private static final Integer FILTER_PARAM = 4;

    public static TimeSeriesProfile toDomain(TimeSeriesResponse response) {
        return TimeSeriesProfile.builder()
                .profileType(PROFILE_TYPE)
                .satellite(SATELLITE)
                .preFilter(PRE_FILTER)
                .filter(FILTER)
                .filterParam(FILTER_PARAM)
                .timeSeriesList(equalizeListDimension(response.timeSeriesList(), 10))
                .dateList(equalizeListDimension(response.dateList(), 10))
                .build();
    }

    public static TimeSeriesRequest toApiRequest(AgroAnalysisInput input) {
        return TimeSeriesRequest.builder()
                .profileType(PROFILE_TYPE)
                .satellite(SATELLITE)
                .preFilter(PRE_FILTER)
                .filter(FILTER)
                .filterParam(FILTER_PARAM)
                .latitude(Double.parseDouble(input.latitude()))
                .longitude(Double.parseDouble(input.longitude()))
                .build();
    }
}
