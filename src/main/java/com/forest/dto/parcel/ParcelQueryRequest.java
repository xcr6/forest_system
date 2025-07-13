package com.forest.dto.parcel;

import lombok.Data;

@Data
public class ParcelQueryRequest {
    private String parcelName;
    private Integer usetypeid;

    public String getParcelName() { return parcelName; }
    public void setParcelName(String parcelName) { this.parcelName = parcelName; }
    public Integer getUsetypeid() { return usetypeid; }
    public void setUsetypeid(Integer usetypeid) { this.usetypeid = usetypeid; }
} 