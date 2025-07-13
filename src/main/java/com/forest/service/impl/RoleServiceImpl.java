package com.forest.service.impl;

import com.forest.common.exception.BusinessException;
import com.forest.entity.SysPermission;
import com.forest.entity.SysRole;
import com.forest.repository.SysPermissionRepository;
import com.forest.repository.SysRoleRepository;
import com.forest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleRepository roleRepository;

    @Autowired
    private SysPermissionRepository permissionRepository;

    @Override
    public Page<SysRole> getRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public SysRole getRoleById(Integer roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new BusinessException("角色不存在"));
    }

    @Override
    @Transactional
    public SysRole createRole(SysRole role) {
        if (roleRepository.findByRoleCode(role.getRoleCode()).isPresent()) {
            throw new BusinessException("角色代码已存在");
        }
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public SysRole updateRole(Integer roleId, SysRole role) {
        SysRole existingRole = getRoleById(roleId);
        
        // 检查角色代码是否已存在（排除自身）
        roleRepository.findByRoleCode(role.getRoleCode())
                .ifPresent(r -> {
                    if (!r.getRoleId().equals(roleId)) {
                        throw new BusinessException("角色代码已存在");
                    }
                });
        
        existingRole.setRoleCode(role.getRoleCode());
        existingRole.setRoleName(role.getRoleName());
        existingRole.setRoleDescription(role.getRoleDescription());
        existingRole.setRoleStatus(role.getRoleStatus());
        
        return roleRepository.save(existingRole);
    }

    @Override
    @Transactional
    public void deleteRole(Integer roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new BusinessException("角色不存在");
        }
        roleRepository.deleteById(roleId);
    }

    @Override
    @Transactional
    public void assignPermissions(Integer roleId, Set<Integer> permissionIds) {
        SysRole role = getRoleById(roleId);
        Set<SysPermission> permissions = new HashSet<>();
        
        for (Integer permissionId : permissionIds) {
            SysPermission permission = permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new BusinessException("权限不存在: " + permissionId));
            permissions.add(permission);
        }
        
        role.setPermissions(permissions);
        roleRepository.save(role);
    }

    @Override
    public List<SysRole> getAllRoles() {
        return roleRepository.findAll();
    }
} 