package com.forest.service;

import com.forest.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<SysUser> getUsers(Pageable pageable);
    
    SysUser getUserById(Integer userId);
    
    SysUser createUser(SysUser user);
    
    SysUser updateUser(Integer userId, SysUser user);
    
    void deleteUser(Integer userId);
    
    void changePassword(Integer userId, String oldPassword, String newPassword);
    
    void changeUserStatus(Integer userId, Short status);
} 