package com.forest.dto.parcel;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ParcelDTO {
    private Integer parcelId;

    @NotBlank(message = "地块编码不能为空")
    private String parcelCode;

    @NotBlank(message = "地块名称不能为空")
    private String parcelName;

    private BigDecimal area;

    private BigDecimal perimeter;

    private String aspect;

    @NotNull(message = "土地利用类型不能为空")
    private Integer usetypeid;

    private String remarks;

    // 扩展字段，用于显示
    private String useTypeName;

    public Integer getParcelId() { return parcelId; }
    public void setParcelId(Integer parcelId) { this.parcelId = parcelId; }
    public String getParcelCode() { return parcelCode; }
    public void setParcelCode(String parcelCode) { this.parcelCode = parcelCode; }
    public String getParcelName() { return parcelName; }
    public void setParcelName(String parcelName) { this.parcelName = parcelName; }
    public java.math.BigDecimal getArea() { return area; }
    public void setArea(java.math.BigDecimal area) { this.area = area; }
    public java.math.BigDecimal getPerimeter() { return perimeter; }
    public void setPerimeter(java.math.BigDecimal perimeter) { this.perimeter = perimeter; }
    public String getAspect() { return aspect; }
    public void setAspect(String aspect) { this.aspect = aspect; }
    public Integer getUsetypeid() { return usetypeid; }
    public void setUsetypeid(Integer usetypeid) { this.usetypeid = usetypeid; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public String getUseTypeName() { return useTypeName; }
    public void setUseTypeName(String useTypeName) { this.useTypeName = useTypeName; }
} 