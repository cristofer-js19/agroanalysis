package com.tech.agroanalysis.application.port.out;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.domain.model.GenerativeAiAnalysis;
import com.tech.agroanalysis.domain.model.TimeSeriesProfile;
import com.tech.agroanalysis.domain.model.WeatherForecastProfile;

public interface GenerativeAiDataPort {
    GenerativeAiAnalysis generateAnalysis(AgroAnalysisInput input, TimeSeriesProfile timeSeriesProfile,
                                          WeatherForecastProfile weatherForecastProfile) throws Exception;
}
