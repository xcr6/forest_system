package com.forest.dto.species;

import lombok.Data;

@Data
public class SpeciesQueryRequest {
    private String speciesName;
    private String speciesCode;
    private String familyName;
    private String genusName;

    public String getSpeciesName() { return speciesName; }
    public void setSpeciesName(String speciesName) { this.speciesName = speciesName; }
    public String getSpeciesCode() { return speciesCode; }
    public void setSpeciesCode(String speciesCode) { this.speciesCode = speciesCode; }
    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }
    public String getGenusName() { return genusName; }
    public void setGenusName(String genusName) { this.genusName = genusName; }
} 