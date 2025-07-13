package com.forest.controller;

import com.forest.common.api.ApiResponse;
import com.forest.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/statistics")
    public ApiResponse<?> getStatistics() {
        return ApiResponse.success(dashboardService.getStatistics());
    }
} 