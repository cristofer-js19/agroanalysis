package com.tech.agroanalysis.domain.model;

import lombok.Builder;

import java.util.List;

@Builder
public record TimeSeriesProfile(
        String profileType,
        String satellite,
        Integer preFilter,
        String filter,
        Integer filterParam,
        List<Double> timeSeriesList,
        List<String> dateList
) {}
