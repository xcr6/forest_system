package com.forest.service.impl;

import com.forest.common.exception.BusinessException;
import com.forest.dto.usetype.UseTypeDTO;
import com.forest.dto.usetype.UseTypeQueryRequest;
import com.forest.entity.LandUseType;
import com.forest.repository.LandUseTypeRepository;
import com.forest.service.LandUseTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LandUseTypeServiceImpl implements LandUseTypeService {

    @Autowired
    private LandUseTypeRepository useTypeRepository;

    @Override
    public Page<UseTypeDTO> getUseTypes(UseTypeQueryRequest queryRequest, Pageable pageable) {
        Page<LandUseType> useTypes = useTypeRepository.findByConditions(
                queryRequest.getUseTypeName(),
                queryRequest.getUseTypeCode(),
                pageable
        );
        
        return useTypes.map(this::convertToDTO);
    }

    @Override
    public UseTypeDTO getUseTypeById(Integer useTypeId) {
        return convertToDTO(getUseTypeEntityById(useTypeId));
    }

    @Override
    @Transactional
    public UseTypeDTO createUseType(UseTypeDTO useTypeDTO) {
        // 验证土地利用类型编码唯一性
        if (useTypeRepository.existsByUseTypeCode(useTypeDTO.getUseTypeCode())) {
            throw new BusinessException("土地利用类型编码已存在");
        }

        LandUseType useType = new LandUseType();
        BeanUtils.copyProperties(useTypeDTO, useType);

        return convertToDTO(useTypeRepository.save(useType));
    }

    @Override
    @Transactional
    public UseTypeDTO updateUseType(Integer useTypeId, UseTypeDTO useTypeDTO) {
        LandUseType existingUseType = getUseTypeEntityById(useTypeId);

        // 验证土地利用类型编码唯一性（排除自身）
        if (!existingUseType.getUseTypeCode().equals(useTypeDTO.getUseTypeCode()) &&
            useTypeRepository.existsByUseTypeCode(useTypeDTO.getUseTypeCode())) {
            throw new BusinessException("土地利用类型编码已存在");
        }

        BeanUtils.copyProperties(useTypeDTO, existingUseType);

        return convertToDTO(useTypeRepository.save(existingUseType));
    }

    @Override
    @Transactional
    public void deleteUseType(Integer useTypeId) {
        LandUseType useType = getUseTypeEntityById(useTypeId);
        
        // 检查是否存在关联的地块
        if (!useType.getParcels().isEmpty()) {
            throw new BusinessException("该土地利用类型已被地块使用，不能删除");
        }
        
        useTypeRepository.deleteById(useTypeId);
    }

    @Override
    public boolean existsByUseTypeCode(String useTypeCode) {
        return useTypeRepository.existsByUseTypeCode(useTypeCode);
    }

    @Override
    public LandUseType getUseTypeEntityById(Integer useTypeId) {
        return useTypeRepository.findById(useTypeId)
                .orElseThrow(() -> new BusinessException("土地利用类型不存在"));
    }

    @Override
    public List<UseTypeDTO> getAllUseTypes() {
        return useTypeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UseTypeDTO convertToDTO(LandUseType useType) {
        UseTypeDTO dto = new UseTypeDTO();
        BeanUtils.copyProperties(useType, dto);
        return dto;
    }
} 