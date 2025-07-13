package com.forest.controller;

import com.forest.common.api.ApiResponse;
import com.forest.entity.SysPermission;
import com.forest.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/tree")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse<List<SysPermission>> getPermissionTree() {
        return ApiResponse.success(permissionService.getPermissionTree());
    }
} 