package com.forest.controller;

import com.forest.common.api.ApiResponse;
import com.forest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/assignPermission")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse<?> assignPermission(@RequestBody Map<String, Object> req) {
        Integer roleId = (Integer) req.get("roleId");
        List<Integer> permissionIds = (List<Integer>) req.get("permissionIds");
        roleService.assignPermissions(roleId, new HashSet<>(permissionIds));
        return ApiResponse.success();
    }
} 