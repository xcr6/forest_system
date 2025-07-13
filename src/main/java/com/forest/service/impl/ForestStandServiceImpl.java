package com.forest.service.impl;

import com.forest.common.exception.BusinessException;
import com.forest.dto.stand.StandDTO;
import com.forest.dto.stand.StandQueryRequest;
import com.forest.dto.stand.StandSpeciesDTO;
import com.forest.entity.ForestParcel;
import com.forest.entity.ForestStand;
import com.forest.entity.StandSpecies;
import com.forest.entity.TreeSpecies;
import com.forest.repository.ForestStandRepository;
import com.forest.repository.StandSpeciesRepository;
import com.forest.repository.TreeSpeciesRepository;
import com.forest.service.ForestParcelService;
import com.forest.service.ForestStandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForestStandServiceImpl implements ForestStandService {

    @Autowired
    private ForestStandRepository standRepository;

    @Autowired
    private ForestParcelService parcelService;

    @Autowired
    private TreeSpeciesRepository treeSpeciesRepository;

    @Autowired
    private StandSpeciesRepository standSpeciesRepository;

    @Override
    public Page<StandDTO> getStands(StandQueryRequest queryRequest, Pageable pageable) {
        Page<ForestStand> stands = standRepository.findByConditions(
                queryRequest.getStandName(),
                queryRequest.getParcelId(),
                queryRequest.getAspect(),
                queryRequest.getSlope(),
                pageable
        );
        
        return stands.map(this::convertToDTO);
    }

    @Override
    public StandDTO getStandById(Integer standId) {
        return convertToDTO(getStandEntityById(standId));
    }

    @Override
    @Transactional
    public StandDTO createStand(StandDTO standDTO) {
        // 验证林分编码唯一性
        if (standRepository.existsByStandCode(standDTO.getStandCode())) {
            throw new BusinessException("林分编码已存在");
        }

        // 验证地块是否存在
        ForestParcel parcel = parcelService.getParcelEntityById(standDTO.getParcelId());

        ForestStand stand = new ForestStand();
        BeanUtils.copyProperties(standDTO, stand);
        stand.setParcel(parcel);

        // 保存林分
        stand = standRepository.save(stand);

        // 处理林分树种
        if (standDTO.getSpecies() != null && !standDTO.getSpecies().isEmpty()) {
            List<StandSpecies> standSpecies = createStandSpecies(stand, standDTO.getSpecies());
            stand.setSpecies(new HashSet<>(standSpecies));
        }

        return convertToDTO(stand);
    }

    @Override
    @Transactional
    public StandDTO updateStand(Integer standId, StandDTO standDTO) {
        ForestStand existingStand = getStandEntityById(standId);

        // 验证林分编码唯一性（排除自身）
        if (!existingStand.getStandCode().equals(standDTO.getStandCode()) &&
            standRepository.existsByStandCode(standDTO.getStandCode())) {
            throw new BusinessException("林分编码已存在");
        }

        // 验证地块是否存在
        ForestParcel parcel = parcelService.getParcelEntityById(standDTO.getParcelId());

        BeanUtils.copyProperties(standDTO, existingStand);
        existingStand.setParcel(parcel);

        // 更新林分树种
        if (standDTO.getSpecies() != null) {
            // 删除原有的林分树种
            standSpeciesRepository.deleteByStand(existingStand);
            
            // 创建新的林分树种
            List<StandSpecies> standSpecies = createStandSpecies(existingStand, standDTO.getSpecies());
            existingStand.setSpecies(new HashSet<>(standSpecies));
        }

        return convertToDTO(standRepository.save(existingStand));
    }

    @Override
    @Transactional
    public void deleteStand(Integer standId) {
        ForestStand stand = getStandEntityById(standId);
        
        // 删除关联的林分树种
        standSpeciesRepository.deleteByStand(stand);
        
        // 删除林分
        standRepository.deleteById(standId);
    }

    @Override
    public boolean existsByStandCode(String standCode) {
        return standRepository.existsByStandCode(standCode);
    }

    @Override
    public ForestStand getStandEntityById(Integer standId) {
        return standRepository.findById(standId)
                .orElseThrow(() -> new BusinessException("林分不存在"));
    }

    private StandDTO convertToDTO(ForestStand stand) {
        StandDTO dto = new StandDTO();
        BeanUtils.copyProperties(stand, dto);
        
        // 设置地块ID和名称
        if (stand.getParcel() != null) {
            dto.setParcelId(stand.getParcel().getParcelId());
            dto.setParcelName(stand.getParcel().getParcelName());
        }
        
        // 设置林分树种
        if (stand.getSpecies() != null && !stand.getSpecies().isEmpty()) {
            dto.setSpecies(stand.getSpecies().stream()
                    .map(this::convertToStandSpeciesDTO)
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }

    private StandSpeciesDTO convertToStandSpeciesDTO(StandSpecies standSpecies) {
        StandSpeciesDTO dto = new StandSpeciesDTO();
        BeanUtils.copyProperties(standSpecies, dto);
        
        // 设置树种信息
        TreeSpecies species = standSpecies.getSpecies();
        if (species != null) {
            dto.setSpeciesId(species.getSpeciesId());
            dto.setSpeciesName(species.getSpeciesName());
            dto.setSpeciesCode(species.getSpeciesCode());
        }
        
        return dto;
    }

    private List<StandSpecies> createStandSpecies(ForestStand stand, List<StandSpeciesDTO> speciesDTOs) {
        List<StandSpecies> standSpecies = new ArrayList<>();
        
        for (StandSpeciesDTO speciesDTO : speciesDTOs) {
            TreeSpecies treeSpecies = treeSpeciesRepository.findById(speciesDTO.getSpeciesId())
                    .orElseThrow(() -> new BusinessException("树种不存在: " + speciesDTO.getSpeciesId()));

            StandSpecies species = new StandSpecies();
            BeanUtils.copyProperties(speciesDTO, species);
            species.setStand(stand);
            species.setSpecies(treeSpecies);
            
            standSpecies.add(standSpeciesRepository.save(species));
        }
        
        return standSpecies;
    }
} 