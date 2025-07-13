package com.forest.service;

import com.forest.dto.stand.StandDTO;
import com.forest.dto.stand.StandQueryRequest;
import com.forest.entity.ForestStand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ForestStandService {
    Page<StandDTO> getStands(StandQueryRequest queryRequest, Pageable pageable);
    
    StandDTO getStandById(Integer standId);
    
    StandDTO createStand(StandDTO standDTO);
    
    StandDTO updateStand(Integer standId, StandDTO standDTO);
    
    void deleteStand(Integer standId);
    
    boolean existsByStandCode(String standCode);
    
    ForestStand getStandEntityById(Integer standId);
} 