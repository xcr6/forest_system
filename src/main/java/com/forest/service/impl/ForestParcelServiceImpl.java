package com.forest.service.impl;

import com.forest.common.exception.BusinessException;
import com.forest.dto.parcel.ParcelDTO;
import com.forest.dto.parcel.ParcelQueryRequest;
import com.forest.entity.ForestParcel;
import com.forest.entity.LandUseType;
import com.forest.repository.ForestParcelRepository;
import com.forest.repository.LandUseTypeRepository;
import com.forest.service.ForestParcelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ForestParcelServiceImpl implements ForestParcelService {

    @Autowired
    private ForestParcelRepository parcelRepository;

    @Autowired
    private LandUseTypeRepository landUseTypeRepository;

    @Override
    public Page<ParcelDTO> getParcels(ParcelQueryRequest queryRequest, Pageable pageable) {
        Page<ForestParcel> parcels = parcelRepository.findByConditions(
                queryRequest.getParcelName(),
                queryRequest.getUsetypeid(),
                pageable
        );
        
        return parcels.map(this::convertToDTO);
    }

    @Override
    public ParcelDTO getParcelById(Integer parcelId) {
        return convertToDTO(getParcelEntityById(parcelId));
    }

    @Override
    @Transactional
    public ParcelDTO createParcel(ParcelDTO parcelDTO) {
        // 验证地块编码唯一性
        if (parcelRepository.existsByParcelCode(parcelDTO.getParcelCode())) {
            throw new BusinessException("地块编码已存在");
        }

        // 验证土地利用类型是否存在
        LandUseType landUseType = landUseTypeRepository.findById(parcelDTO.getUsetypeid())
                .orElseThrow(() -> new BusinessException("土地利用类型不存在"));

        ForestParcel parcel = new ForestParcel();
        BeanUtils.copyProperties(parcelDTO, parcel);
        parcel.setLandUseType(landUseType);

        return convertToDTO(parcelRepository.save(parcel));
    }

    @Override
    @Transactional
    public ParcelDTO updateParcel(Integer parcelId, ParcelDTO parcelDTO) {
        ForestParcel existingParcel = getParcelEntityById(parcelId);

        // 验证地块编码唯一性（排除自身）
        if (!existingParcel.getParcelCode().equals(parcelDTO.getParcelCode()) &&
            parcelRepository.existsByParcelCode(parcelDTO.getParcelCode())) {
            throw new BusinessException("地块编码已存在");
        }

        // 验证土地利用类型是否存在
        LandUseType landUseType = landUseTypeRepository.findById(parcelDTO.getUsetypeid())
                .orElseThrow(() -> new BusinessException("土地利用类型不存在"));

        BeanUtils.copyProperties(parcelDTO, existingParcel);
        existingParcel.setLandUseType(landUseType);

        return convertToDTO(parcelRepository.save(existingParcel));
    }

    @Override
    @Transactional
    public void deleteParcel(Integer parcelId) {
        ForestParcel parcel = getParcelEntityById(parcelId);
        
        // 检查是否存在关联的林分
        if (!parcel.getStands().isEmpty()) {
            throw new BusinessException("该地块存在关联的林分，不能删除");
        }
        
        parcelRepository.deleteById(parcelId);
    }

    @Override
    public boolean existsByParcelCode(String parcelCode) {
        return parcelRepository.existsByParcelCode(parcelCode);
    }

    @Override
    public ForestParcel getParcelEntityById(Integer parcelId) {
        return parcelRepository.findById(parcelId)
                .orElseThrow(() -> new BusinessException("地块不存在"));
    }

    private ParcelDTO convertToDTO(ForestParcel parcel) {
        ParcelDTO dto = new ParcelDTO();
        BeanUtils.copyProperties(parcel, dto);
        
        // 设置土地利用类型ID和名称
        if (parcel.getLandUseType() != null) {
            dto.setUsetypeid(parcel.getLandUseType().getUseTypeId());
            dto.setUseTypeName(parcel.getLandUseType().getUseTypeName());
        }
        
        return dto;
    }
} 