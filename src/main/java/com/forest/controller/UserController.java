package com.forest.controller;

import com.forest.common.api.ApiResponse;
import com.forest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/changeStatus")
    @PreAuthorize("hasAuthority('USER_STATUS_UPDATE') or hasRole('ROLE_ADMIN')")
    public ApiResponse<?> changeStatus(@RequestBody Map<String, Object> req) {
        Integer userId = (Integer) req.get("userId");
        Short status = ((Number) req.get("status")).shortValue();
        userService.changeUserStatus(userId, status);
        return ApiResponse.success();
    }
} 