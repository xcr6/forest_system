package com.forest.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "land_use_type")
public class LandUseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_type_id")
    private Integer useTypeId;

    @Column(name = "use_type_code", nullable = false, unique = true)
    private String useTypeCode;

    @Column(name = "use_type_name", nullable = false)
    private String useTypeName;

    @Column(name = "use_type_desc")
    private String useTypeDesc;

    @OneToMany(mappedBy = "landUseType", fetch = FetchType.LAZY)
    private Set<ForestParcel> parcels;

    public Integer getUseTypeId() { return useTypeId; }
    public void setUseTypeId(Integer useTypeId) { this.useTypeId = useTypeId; }
    public String getUseTypeCode() { return useTypeCode; }
    public void setUseTypeCode(String useTypeCode) { this.useTypeCode = useTypeCode; }
    public String getUseTypeName() { return useTypeName; }
    public void setUseTypeName(String useTypeName) { this.useTypeName = useTypeName; }
    public String getUseTypeDesc() { return useTypeDesc; }
    public void setUseTypeDesc(String useTypeDesc) { this.useTypeDesc = useTypeDesc; }
    public java.util.Set<ForestParcel> getParcels() { return parcels; }
    public void setParcels(java.util.Set<ForestParcel> parcels) { this.parcels = parcels; }
} 