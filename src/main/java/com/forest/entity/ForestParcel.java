package com.forest.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "forest_parcel")
public class ForestParcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parcel_id")
    private Integer parcelId;

    @Column(name = "parcel_code", nullable = false, unique = true)
    private String parcelCode;

    @Column(name = "parcel_name", nullable = false)
    private String parcelName;

    @Column(name = "area", precision = 10, scale = 4)
    private BigDecimal area;

    @Column(name = "perimeter", precision = 10, scale = 4)
    private BigDecimal perimeter;

    @Column(name = "aspect")
    private String aspect;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usetypeid")
    private LandUseType landUseType;

    @OneToMany(mappedBy = "parcel", fetch = FetchType.LAZY)
    private Set<ForestStand> stands;

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
    public java.util.Date getCreateTime() { return createTime; }
    public void setCreateTime(java.util.Date createTime) { this.createTime = createTime; }
    public java.util.Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(java.util.Date updateTime) { this.updateTime = updateTime; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public LandUseType getLandUseType() { return landUseType; }
    public void setLandUseType(LandUseType landUseType) { this.landUseType = landUseType; }
    public java.util.Set<ForestStand> getStands() { return stands; }
    public void setStands(java.util.Set<ForestStand> stands) { this.stands = stands; }
} 