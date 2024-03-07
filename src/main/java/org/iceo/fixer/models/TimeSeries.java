package org.iceo.fixer.models;

import lombok.Data;

@Data
public class TimeSeries {

    public String base;
    public String end_date;
    public Object rates;
    public String start_date;
    public Boolean success;
    public Boolean timeseries;

}
