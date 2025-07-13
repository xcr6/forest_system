package com.forest.service.impl;

import com.forest.common.exception.BusinessException;
import com.forest.dto.species.SpeciesDTO;
import com.forest.dto.species.SpeciesQueryRequest;
import com.forest.entity.TreeSpecies;
import com.forest.repository.StandSpeciesRepository;
import com.forest.repository.TreeSpeciesRepository;
import com.forest.service.TreeSpeciesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeSpeciesServiceImpl implements TreeSpeciesService {

    @Autowired
    private TreeSpeciesRepository speciesRepository;

    @Autowired
    private StandSpeciesRepository standSpeciesRepository;

    @Override
    public Page<SpeciesDTO> getSpecies(SpeciesQueryRequest queryRequest, Pageable pageable) {
        Page<TreeSpecies> species = speciesRepository.findByConditions(
                queryRequest.getSpeciesName(),
                queryRequest.getSpeciesCode(),
                pageable
        );
        
        return species.map(this::convertToDTO);
    }

    @Override
    public SpeciesDTO getSpeciesById(Integer speciesId) {
        return convertToDTO(getSpeciesEntityById(speciesId));
    }

    @Override
    @Transactional
    public SpeciesDTO createSpecies(SpeciesDTO speciesDTO) {
        // 验证树种编码唯一性
        if (speciesRepository.existsBySpeciesCode(speciesDTO.getSpeciesCode())) {
            throw new BusinessException("树种编码已存在");
        }

        TreeSpecies species = new TreeSpecies();
        BeanUtils.copyProperties(speciesDTO, species);

        return convertToDTO(speciesRepository.save(species));
    }

    @Override
    @Transactional
    public SpeciesDTO updateSpecies(Integer speciesId, SpeciesDTO speciesDTO) {
        TreeSpecies existingSpecies = getSpeciesEntityById(speciesId);

        // 验证树种编码唯一性（排除自身）
        if (!existingSpecies.getSpeciesCode().equals(speciesDTO.getSpeciesCode()) &&
            speciesRepository.existsBySpeciesCode(speciesDTO.getSpeciesCode())) {
            throw new BusinessException("树种编码已存在");
        }

        BeanUtils.copyProperties(speciesDTO, existingSpecies);

        return convertToDTO(speciesRepository.save(existingSpecies));
    }

    @Override
    @Transactional
    public void deleteSpecies(Integer speciesId) {
        TreeSpecies species = getSpeciesEntityById(speciesId);
        
        // 检查是否存在关联的林分树种
        if (!species.getStands().isEmpty()) {
            throw new BusinessException("该树种已被林分使用，不能删除");
        }
        
        speciesRepository.deleteById(speciesId);
    }

    @Override
    public boolean existsBySpeciesCode(String speciesCode) {
        return speciesRepository.existsBySpeciesCode(speciesCode);
    }

    @Override
    public TreeSpecies getSpeciesEntityById(Integer speciesId) {
        return speciesRepository.findById(speciesId)
                .orElseThrow(() -> new BusinessException("树种不存在"));
    }

    @Override
    public List<SpeciesDTO> getAllSpecies() {
        return speciesRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SpeciesDTO convertToDTO(TreeSpecies species) {
        SpeciesDTO dto = new SpeciesDTO();
        BeanUtils.copyProperties(species, dto);
        return dto;
    }
} 