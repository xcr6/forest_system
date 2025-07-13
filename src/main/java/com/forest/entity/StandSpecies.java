package com.forest.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "stand_species")
public class StandSpecies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_id")
    private Integer relationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stand_id", nullable = false)
    private ForestStand stand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "species_id", nullable = false)
    private TreeSpecies species;

    @Column(name = "proportion", nullable = false, precision = 5, scale = 2)
    private BigDecimal proportion;

    @Column(name = "density", precision = 5, scale = 2)
    private BigDecimal density;

    @Column(name = "average_height", precision = 5, scale = 2)
    private BigDecimal averageHeight;

    @Column(name = "average_dbh", precision = 5, scale = 2)
    private BigDecimal averageDbh;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private Date createTime;

    public Integer getRelationId() { return relationId; }
    public void setRelationId(Integer relationId) { this.relationId = relationId; }
    public ForestStand getStand() { return stand; }
    public void setStand(ForestStand stand) { this.stand = stand; }
    public TreeSpecies getSpecies() { return species; }
    public void setSpecies(TreeSpecies species) { this.species = species; }
    public java.math.BigDecimal getProportion() { return proportion; }
    public void setProportion(java.math.BigDecimal proportion) { this.proportion = proportion; }
    public java.math.BigDecimal getDensity() { return density; }
    public void setDensity(java.math.BigDecimal density) { this.density = density; }
    public java.math.BigDecimal getAverageHeight() { return averageHeight; }
    public void setAverageHeight(java.math.BigDecimal averageHeight) { this.averageHeight = averageHeight; }
    public java.math.BigDecimal getAverageDbh() { return averageDbh; }
    public void setAverageDbh(java.math.BigDecimal averageDbh) { this.averageDbh = averageDbh; }
    public java.util.Date getCreateTime() { return createTime; }
    public void setCreateTime(java.util.Date createTime) { this.createTime = createTime; }
} 