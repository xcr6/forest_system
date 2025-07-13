package com.forest.dto.stand;

import lombok.Data;

@Data
public class StandQueryRequest {
    private String standName;
    private Integer parcelId;
    private String aspect;
    private String slope;

    public String getStandName() { return standName; }
    public void setStandName(String standName) { this.standName = standName; }
    public Integer getParcelId() { return parcelId; }
    public void setParcelId(Integer parcelId) { this.parcelId = parcelId; }
    public String getAspect() { return aspect; }
    public void setAspect(String aspect) { this.aspect = aspect; }
    public String getSlope() { return slope; }
    public void setSlope(String slope) { this.slope = slope; }
} 