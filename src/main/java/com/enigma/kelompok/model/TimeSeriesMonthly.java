package com.enigma.kelompok.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeSeriesMonthly {
    @JsonProperty("Meta Data")
    private MetaData metaData;

    @JsonProperty("Monthly Time Series")
    private Map<String, MonthlyData> monthlyTimeSeries;
}
