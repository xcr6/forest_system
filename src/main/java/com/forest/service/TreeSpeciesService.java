package com.forest.service;

import com.forest.dto.species.SpeciesDTO;
import com.forest.dto.species.SpeciesQueryRequest;
import com.forest.entity.TreeSpecies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TreeSpeciesService {
    Page<SpeciesDTO> getSpecies(SpeciesQueryRequest queryRequest, Pageable pageable);
    
    SpeciesDTO getSpeciesById(Integer speciesId);
    
    SpeciesDTO createSpecies(SpeciesDTO speciesDTO);
    
    SpeciesDTO updateSpecies(Integer speciesId, SpeciesDTO speciesDTO);
    
    void deleteSpecies(Integer speciesId);
    
    boolean existsBySpeciesCode(String speciesCode);
    
    TreeSpecies getSpeciesEntityById(Integer speciesId);
    
    List<SpeciesDTO> getAllSpecies();
} 