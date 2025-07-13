package com.forest.service.impl;

import com.forest.entity.ForestParcel;
import com.forest.entity.LandUseType;
import com.forest.repository.ForestParcelRepository;
import com.forest.repository.ForestStandRepository;
import com.forest.repository.LandUseTypeRepository;
import com.forest.repository.TreeSpeciesRepository;
import com.forest.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private ForestParcelRepository parcelRepository;
    @Autowired
    private ForestStandRepository standRepository;
    @Autowired
    private TreeSpeciesRepository speciesRepository;
    @Autowired
    private LandUseTypeRepository landUseTypeRepository;

    @Override
    public Map<String, Object> getStatistics() {
        Map<String, Object> result = new HashMap<>();
        long parcelCount = parcelRepository.count();
        long standCount = standRepository.count();
        long speciesCount = speciesRepository.count();
        BigDecimal totalArea = parcelRepository.findAll().stream()
                .map(ForestParcel::getArea)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 土地利用类型分布
        List<Map<String, Object>> landUseTypeDistribution = landUseTypeRepository.findAll().stream()
                .map(type -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", type.getUseTypeName());
                    BigDecimal area = parcelRepository.findAll().stream()
                            .filter(p -> p.getLandUseType() != null && Objects.equals(p.getLandUseType().getUseTypeId(), type.getUseTypeId()))
                            .map(ForestParcel::getArea)
                            .filter(Objects::nonNull)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    map.put("value", area);
                    return map;
                })
                .collect(Collectors.toList());
        result.put("parcelCount", parcelCount);
        result.put("standCount", standCount);
        result.put("speciesCount", speciesCount);
        result.put("totalArea", totalArea);
        result.put("landUseTypeDistribution", landUseTypeDistribution);
        return result;
    }
} 