package com.forest.service;

import com.forest.dto.usetype.UseTypeDTO;
import com.forest.dto.usetype.UseTypeQueryRequest;
import com.forest.entity.LandUseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LandUseTypeService {
    Page<UseTypeDTO> getUseTypes(UseTypeQueryRequest queryRequest, Pageable pageable);
    
    UseTypeDTO getUseTypeById(Integer useTypeId);
    
    UseTypeDTO createUseType(UseTypeDTO useTypeDTO);
    
    UseTypeDTO updateUseType(Integer useTypeId, UseTypeDTO useTypeDTO);
    
    void deleteUseType(Integer useTypeId);
    
    boolean existsByUseTypeCode(String useTypeCode);
    
    LandUseType getUseTypeEntityById(Integer useTypeId);
    
    List<UseTypeDTO> getAllUseTypes();
} 