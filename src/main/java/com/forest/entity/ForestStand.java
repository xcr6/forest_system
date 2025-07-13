package com.forest.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "forest_stand")
public class ForestStand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stand_id")
    private Integer standId;

    @Column(name = "stand_code", nullable = false, unique = true)
    private String standCode;

    @Column(name = "stand_name", nullable = false)
    private String standName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parcel_id")
    private ForestParcel parcel;

    @Column(name = "foresttype_id")
    private Integer forestTypeId;

    @Column(name = "density", precision = 5, scale = 2)
    private BigDecimal density;

    @Column(name = "crown_density", precision = 3, scale = 2)
    private BigDecimal crownDensity;

    @Column(name = "average_height", precision = 5, scale = 2)
    private BigDecimal averageHeight;

    @Column(name = "average_dbh", precision = 5, scale = 2)
    private BigDecimal averageDbh;

    @Column(name = "basal_area", precision = 6, scale = 2)
    private BigDecimal basalArea;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "remarks")
    private String remarks;

    @OneToMany(mappedBy = "stand", fetch = FetchType.LAZY)
    private Set<StandSpecies> species;

    public Integer getStandId() { return standId; }
    public void setStandId(Integer standId) { this.standId = standId; }
    public String getStandCode() { return standCode; }
    public void setStandCode(String standCode) { this.standCode = standCode; }
    public String getStandName() { return standName; }
    public void setStandName(String standName) { this.standName = standName; }
    public ForestParcel getParcel() { return parcel; }
    public void setParcel(ForestParcel parcel) { this.parcel = parcel; }
    public Integer getForestTypeId() { return forestTypeId; }
    public void setForestTypeId(Integer forestTypeId) { this.forestTypeId = forestTypeId; }
    public java.math.BigDecimal getDensity() { return density; }
    public void setDensity(java.math.BigDecimal density) { this.density = density; }
    public java.math.BigDecimal getCrownDensity() { return crownDensity; }
    public void setCrownDensity(java.math.BigDecimal crownDensity) { this.crownDensity = crownDensity; }
    public java.math.BigDecimal getAverageHeight() { return averageHeight; }
    public void setAverageHeight(java.math.BigDecimal averageHeight) { this.averageHeight = averageHeight; }
    public java.math.BigDecimal getAverageDbh() { return averageDbh; }
    public void setAverageDbh(java.math.BigDecimal averageDbh) { this.averageDbh = averageDbh; }
    public java.math.BigDecimal getBasalArea() { return basalArea; }
    public void setBasalArea(java.math.BigDecimal basalArea) { this.basalArea = basalArea; }
    public java.util.Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(java.util.Date updateTime) { this.updateTime = updateTime; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public java.util.Set<StandSpecies> getSpecies() { return species; }
    public void setSpecies(java.util.Set<StandSpecies> species) { this.species = species; }
} 