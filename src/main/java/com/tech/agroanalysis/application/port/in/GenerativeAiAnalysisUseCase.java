package com.tech.agroanalysis.application.port.in;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.application.dto.AgroAnalysisOutput;

public interface GenerativeAiAnalysisUseCase {
    AgroAnalysisOutput execute(AgroAnalysisInput input);
}
