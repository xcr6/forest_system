package com.forest.service.impl;

import com.forest.dto.auth.LoginRequest;
import com.forest.dto.auth.LoginResponse;
import com.forest.entity.SysUser;
import com.forest.security.jwt.JwtTokenUtil;
import com.forest.security.service.CustomUserDetails;
import com.forest.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // 进行身份验证
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // 生成JWT令牌
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(userDetails);

        // 构建响应
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUser(buildUserInfo(userDetails.getUser()));

        return response;
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @Override
    public LoginResponse.UserInfo getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return buildUserInfo(userDetails.getUser());
        }
        return null;
    }

    private LoginResponse.UserInfo buildUserInfo(SysUser user) {
        LoginResponse.UserInfo userInfo = new LoginResponse.UserInfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setUsername(user.getUsername());
        userInfo.setFullName(user.getFullName());
        userInfo.setRoles(user.getRoles().stream()
                .map(role -> role.getRoleCode())
                .collect(Collectors.toList()));
        return userInfo;
    }
} 