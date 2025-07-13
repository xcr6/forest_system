package com.forest.dto.auth;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
    private String token;
    private UserInfo user;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public UserInfo getUser() { return user; }
    public void setUser(UserInfo user) { this.user = user; }

    @Data
    public static class UserInfo {
        private Integer userId;
        private String username;
        private String fullName;
        private List<String> roles;

        public Integer getUserId() { return userId; }
        public void setUserId(Integer userId) { this.userId = userId; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }
        public java.util.List<String> getRoles() { return roles; }
        public void setRoles(java.util.List<String> roles) { this.roles = roles; }
    }
} 