package com.forest.service.impl;

import com.forest.entity.ForestParcel;
import com.forest.repository.ForestParcelRepository;
import com.forest.repository.LandUseTypeRepository;
import com.forest.service.ResourceOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResourceOverviewServiceImpl implements ResourceOverviewService {
    @Autowired
    private ForestParcelRepository parcelRepository;
    @Autowired
    private LandUseTypeRepository landUseTypeRepository;

    @Override
    public Map<String, Object> getOverview(Integer landUseType, BigDecimal areaMin, BigDecimal areaMax, String aspect) {
        List<ForestParcel> allParcels = parcelRepository.findAll();
        // 过滤条件
        List<ForestParcel> parcels = allParcels;
        if (landUseType != null) {
            parcels = parcels.stream().filter(p -> p.getLandUseType() != null && Objects.equals(p.getLandUseType().getUseTypeId(), landUseType)).collect(Collectors.toList());
        }
        if (areaMin != null) {
            parcels = parcels.stream().filter(p -> p.getArea() != null && p.getArea().compareTo(areaMin) >= 0).collect(Collectors.toList());
        }
        if (areaMax != null) {
            parcels = parcels.stream().filter(p -> p.getArea() != null && p.getArea().compareTo(areaMax) <= 0).collect(Collectors.toList());
        }
        if (aspect != null && !aspect.isEmpty()) {
            parcels = parcels.stream().filter(p -> aspect.equals(p.getAspect())).collect(Collectors.toList());
        }
        
        final List<ForestParcel> finalParcels = parcels;
        
        int totalCount = finalParcels.size();
        BigDecimal totalArea = finalParcels.stream().map(ForestParcel::getArea).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        // 按土地类型统计
        List<Map<String, Object>> statisticsByLandUse = landUseTypeRepository.findAll().stream().map(type -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", type.getUseTypeName());
            BigDecimal area = finalParcels.stream().filter(p -> p.getLandUseType() != null && Objects.equals(p.getLandUseType().getUseTypeId(), type.getUseTypeId())).map(ForestParcel::getArea).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
            map.put("value", area);
            return map;
        }).collect(Collectors.toList());
        // 按坡向统计
        Map<String, BigDecimal> aspectMap = new HashMap<>();
        for (ForestParcel p : finalParcels) {
            if (p.getAspect() != null && p.getArea() != null) {
                aspectMap.put(p.getAspect(), aspectMap.getOrDefault(p.getAspect(), BigDecimal.ZERO).add(p.getArea()));
            }
        }
        List<Map<String, Object>> statisticsByAspect = aspectMap.entrySet().stream().map(e -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", e.getKey());
            map.put("value", e.getValue());
            return map;
        }).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", totalCount);
        result.put("totalArea", totalArea);
        result.put("statisticsByLandUse", statisticsByLandUse);
        result.put("statisticsByAspect", statisticsByAspect);
        return result;
    }
} 