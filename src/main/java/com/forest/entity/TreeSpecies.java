package com.forest.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "tree_species")
public class TreeSpecies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "species_id")
    private Integer speciesId;

    @Column(name = "species_code", nullable = false, unique = true)
    private String speciesCode;

    @Column(name = "species_name", nullable = false)
    private String speciesName;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private Date createTime;

    @OneToMany(mappedBy = "species", fetch = FetchType.LAZY)
    private Set<StandSpecies> stands;

    public Integer getSpeciesId() { return speciesId; }
    public void setSpeciesId(Integer speciesId) { this.speciesId = speciesId; }
    public String getSpeciesCode() { return speciesCode; }
    public void setSpeciesCode(String speciesCode) { this.speciesCode = speciesCode; }
    public String getSpeciesName() { return speciesName; }
    public void setSpeciesName(String speciesName) { this.speciesName = speciesName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public java.util.Date getCreateTime() { return createTime; }
    public void setCreateTime(java.util.Date createTime) { this.createTime = createTime; }
    public java.util.Set<StandSpecies> getStands() { return stands; }
    public void setStands(java.util.Set<StandSpecies> stands) { this.stands = stands; }
} 