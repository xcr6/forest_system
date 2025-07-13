package com.forest.service;

import com.forest.dto.parcel.ParcelDTO;
import com.forest.dto.parcel.ParcelQueryRequest;
import com.forest.entity.ForestParcel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ForestParcelService {
    Page<ParcelDTO> getParcels(ParcelQueryRequest queryRequest, Pageable pageable);
    
    ParcelDTO getParcelById(Integer parcelId);
    
    ParcelDTO createParcel(ParcelDTO parcelDTO);
    
    ParcelDTO updateParcel(Integer parcelId, ParcelDTO parcelDTO);
    
    void deleteParcel(Integer parcelId);
    
    boolean existsByParcelCode(String parcelCode);
    
    ForestParcel getParcelEntityById(Integer parcelId);
} 