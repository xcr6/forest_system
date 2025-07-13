package com.forest.dto.stand;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class StandDTO {
    private Integer standId;

    @NotBlank(message = "林分编码不能为空")
    private String standCode;

    @NotBlank(message = "林分名称不能为空")
    private String standName;

    @NotNull(message = "所属地块不能为空")
    private Integer parcelId;

    private BigDecimal area;
    private String aspect;
    private String slope;
    private String soilType;
    private String hydrology;
    private String remarks;

    // 扩展字段，用于显示
    private String parcelName;
    private List<StandSpeciesDTO> species;

    public Integer getStandId() { return standId; }
    public void setStandId(Integer standId) { this.standId = standId; }
    public String getStandCode() { return standCode; }
    public void setStandCode(String standCode) { this.standCode = standCode; }
    public String getStandName() { return standName; }
    public void setStandName(String standName) { this.standName = standName; }
    public Integer getParcelId() { return parcelId; }
    public void setParcelId(Integer parcelId) { this.parcelId = parcelId; }
    public java.math.BigDecimal getArea() { return area; }
    public void setArea(java.math.BigDecimal area) { this.area = area; }
    public String getAspect() { return aspect; }
    public void setAspect(String aspect) { this.aspect = aspect; }
    public String getSlope() { return slope; }
    public void setSlope(String slope) { this.slope = slope; }
    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }
    public String getHydrology() { return hydrology; }
    public void setHydrology(String hydrology) { this.hydrology = hydrology; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public String getParcelName() { return parcelName; }
    public void setParcelName(String parcelName) { this.parcelName = parcelName; }
    public java.util.List<StandSpeciesDTO> getSpecies() { return species; }
    public void setSpecies(java.util.List<StandSpeciesDTO> species) { this.species = species; }
} 