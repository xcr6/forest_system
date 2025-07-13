package com.forest.dto.species;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class SpeciesDTO {
    private Integer speciesId;

    @NotBlank(message = "树种编码不能为空")
    private String speciesCode;

    @NotBlank(message = "树种名称不能为空")
    private String speciesName;

    private String familyName;
    private String genusName;
    private String latinName;
    private String growthPattern;
    private String economicValue;
    private String protectionValue;
    private String remarks;

    public Integer getSpeciesId() { return speciesId; }
    public void setSpeciesId(Integer speciesId) { this.speciesId = speciesId; }
    public String getSpeciesCode() { return speciesCode; }
    public void setSpeciesCode(String speciesCode) { this.speciesCode = speciesCode; }
    public String getSpeciesName() { return speciesName; }
    public void setSpeciesName(String speciesName) { this.speciesName = speciesName; }
    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }
    public String getGenusName() { return genusName; }
    public void setGenusName(String genusName) { this.genusName = genusName; }
    public String getLatinName() { return latinName; }
    public void setLatinName(String latinName) { this.latinName = latinName; }
    public String getGrowthPattern() { return growthPattern; }
    public void setGrowthPattern(String growthPattern) { this.growthPattern = growthPattern; }
    public String getEconomicValue() { return economicValue; }
    public void setEconomicValue(String economicValue) { this.economicValue = economicValue; }
    public String getProtectionValue() { return protectionValue; }
    public void setProtectionValue(String protectionValue) { this.protectionValue = protectionValue; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
} 