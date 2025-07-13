package com.forest.service;

import java.math.BigDecimal;
import java.util.Map;

public interface ResourceOverviewService {
    Map<String, Object> getOverview(Integer landUseType, BigDecimal areaMin, BigDecimal areaMax, String aspect);
} 