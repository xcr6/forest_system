package com.forest.service;

import com.forest.dto.auth.LoginRequest;
import com.forest.dto.auth.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    
    void logout();
    
    LoginResponse.UserInfo getCurrentUser();
} 