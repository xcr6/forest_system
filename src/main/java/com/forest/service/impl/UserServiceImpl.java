package com.forest.service.impl;

import com.forest.common.exception.BusinessException;
import com.forest.entity.SysUser;
import com.forest.repository.SysUserRepository;
import com.forest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<SysUser> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public SysUser getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("用户不存在"));
    }

    @Override
    @Transactional
    public SysUser createUser(SysUser user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new BusinessException("用户名已存在");
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public SysUser updateUser(Integer userId, SysUser user) {
        SysUser existingUser = getUserById(userId);
        
        // 更新基本信息
        existingUser.setFullName(user.getFullName());
        existingUser.setGender(user.getGender());
        existingUser.setBirthDate(user.getBirthDate());
        existingUser.setContactPhone(user.getContactPhone());
        existingUser.setEmail(user.getEmail());
        existingUser.setRemarks(user.getRemarks());
        
        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new BusinessException("用户不存在");
        }
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void changePassword(Integer userId, String oldPassword, String newPassword) {
        SysUser user = getUserById(userId);
        
        if (!passwordEncoder.matches(oldPassword, user.getPasswordHash())) {
            throw new BusinessException("原密码错误");
        }
        
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void changeUserStatus(Integer userId, Short status) {
        SysUser user = getUserById(userId);
        user.setUserStatus(status);
        userRepository.save(user);
    }
} 