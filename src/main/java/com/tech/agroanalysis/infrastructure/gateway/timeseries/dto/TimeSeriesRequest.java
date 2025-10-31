package com.tech.agroanalysis.infrastructure.gateway.timeseries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record TimeSeriesRequest(
        @JsonProperty("tipoPerfil") String profileType,
        @JsonProperty("satelite") String satellite,
        @JsonProperty("preFiltro") Integer preFilter,
        @JsonProperty("filtro") String filter,
        @JsonProperty("parametroFiltro") Integer filterParam,
        @JsonProperty("poligono") String polygon
) {}
