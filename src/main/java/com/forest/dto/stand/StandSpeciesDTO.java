package com.forest.dto.stand;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class StandSpeciesDTO {
    private Integer standSpeciesId;

    @NotNull(message = "树种不能为空")
    private Integer speciesId;

    private BigDecimal proportion;
    private Integer age;
    private BigDecimal avgHeight;
    private BigDecimal avgDiameter;
    private Integer density;
    private BigDecimal volume;
    private String canopy;
    private String originType;
    private String remarks;

    // 扩展字段，用于显示
    private String speciesName;
    private String speciesCode;

    public Integer getStandSpeciesId() { return standSpeciesId; }
    public void setStandSpeciesId(Integer standSpeciesId) { this.standSpeciesId = standSpeciesId; }
    public Integer getSpeciesId() { return speciesId; }
    public void setSpeciesId(Integer speciesId) { this.speciesId = speciesId; }
    public java.math.BigDecimal getProportion() { return proportion; }
    public void setProportion(java.math.BigDecimal proportion) { this.proportion = proportion; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public java.math.BigDecimal getAvgHeight() { return avgHeight; }
    public void setAvgHeight(java.math.BigDecimal avgHeight) { this.avgHeight = avgHeight; }
    public java.math.BigDecimal getAvgDiameter() { return avgDiameter; }
    public void setAvgDiameter(java.math.BigDecimal avgDiameter) { this.avgDiameter = avgDiameter; }
    public Integer getDensity() { return density; }
    public void setDensity(Integer density) { this.density = density; }
    public java.math.BigDecimal getVolume() { return volume; }
    public void setVolume(java.math.BigDecimal volume) { this.volume = volume; }
    public String getCanopy() { return canopy; }
    public void setCanopy(String canopy) { this.canopy = canopy; }
    public String getOriginType() { return originType; }
    public void setOriginType(String originType) { this.originType = originType; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public String getSpeciesName() { return speciesName; }
    public void setSpeciesName(String speciesName) { this.speciesName = speciesName; }
    public String getSpeciesCode() { return speciesCode; }
    public void setSpeciesCode(String speciesCode) { this.speciesCode = speciesCode; }
} 