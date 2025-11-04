package com.tech.agroanalysis.application.port.out;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.domain.model.TimeSeriesProfile;

public interface TimeSeriesProfileDataPort {
    TimeSeriesProfile getTimeSeriesProfile(AgroAnalysisInput input);
}
