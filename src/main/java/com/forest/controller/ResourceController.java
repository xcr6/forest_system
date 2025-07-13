package com.forest.controller;

import com.forest.common.api.ApiResponse;
import com.forest.service.ResourceOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {
    @Autowired
    private ResourceOverviewService resourceOverviewService;

    @GetMapping("/overview")
    public ApiResponse<?> getOverview(
            @RequestParam(value = "landUseType", required = false) Integer landUseType,
            @RequestParam(value = "areaMin", required = false) BigDecimal areaMin,
            @RequestParam(value = "areaMax", required = false) BigDecimal areaMax,
            @RequestParam(value = "aspect", required = false) String aspect
    ) {
        return ApiResponse.success(resourceOverviewService.getOverview(landUseType, areaMin, areaMax, aspect));
    }
} 