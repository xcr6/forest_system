package com.forest.controller;

import com.forest.common.api.ApiResponse;
import com.forest.dto.auth.LoginRequest;
import com.forest.dto.auth.LoginResponse;
import com.forest.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ApiResponse.success(response);
    }

    @PostMapping("/logout")
    public ApiResponse<?> logout() {
        authService.logout();
        return ApiResponse.success("登出成功");
    }

    @GetMapping("/current")
    public ApiResponse<LoginResponse.UserInfo> getCurrentUser() {
        LoginResponse.UserInfo userInfo = authService.getCurrentUser();
        return ApiResponse.success(userInfo);
    }
} 