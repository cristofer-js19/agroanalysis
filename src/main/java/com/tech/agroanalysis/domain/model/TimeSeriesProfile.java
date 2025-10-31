package com.tech.agroanalysis.domain.model;

import lombok.Builder;

import java.util.List;

@Builder
public record TimeSeriesProfile(
        List<Double> timeSeriesList,
        List<String> dateList
) {}
