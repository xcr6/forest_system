package com.forest.service.impl;

import com.forest.common.exception.BusinessException;
import com.forest.entity.SysPermission;
import com.forest.repository.SysPermissionRepository;
import com.forest.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private SysPermissionRepository permissionRepository;

    @Override
    public List<SysPermission> getPermissionTree() {
        return permissionRepository.findAllRootPermissions();
    }

    @Override
    public SysPermission getPermissionById(Integer permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new BusinessException("权限不存在"));
    }

    @Override
    @Transactional
    public SysPermission createPermission(SysPermission permission) {
        if (permissionRepository.findByPermissionCode(permission.getPermissionCode()).isPresent()) {
            throw new BusinessException("权限代码已存在");
        }
        
        // 如果设置了父权限，验证父权限是否存在
        if (permission.getParent() != null && permission.getParent().getPermissionId() != null) {
            SysPermission parent = getPermissionById(permission.getParent().getPermissionId());
            permission.setParent(parent);
        }
        
        return permissionRepository.save(permission);
    }

    @Override
    @Transactional
    public SysPermission updatePermission(Integer permissionId, SysPermission permission) {
        SysPermission existingPermission = getPermissionById(permissionId);
        
        // 检查权限代码是否已存在（排除自身）
        permissionRepository.findByPermissionCode(permission.getPermissionCode())
                .ifPresent(p -> {
                    if (!p.getPermissionId().equals(permissionId)) {
                        throw new BusinessException("权限代码已存在");
                    }
                });
        
        // 如果设置了父权限，验证父权限是否存在且不能是自己或自己的子权限
        if (permission.getParent() != null && permission.getParent().getPermissionId() != null) {
            if (permission.getParent().getPermissionId().equals(permissionId)) {
                throw new BusinessException("不能将自己设为父权限");
            }
            SysPermission parent = getPermissionById(permission.getParent().getPermissionId());
            existingPermission.setParent(parent);
        } else {
            existingPermission.setParent(null);
        }
        
        existingPermission.setPermissionCode(permission.getPermissionCode());
        existingPermission.setPermissionName(permission.getPermissionName());
        existingPermission.setPermissionType(permission.getPermissionType());
        existingPermission.setRoutePath(permission.getRoutePath());
        existingPermission.setComponentPath(permission.getComponentPath());
        existingPermission.setIcon(permission.getIcon());
        existingPermission.setSortOrder(permission.getSortOrder());
        existingPermission.setPermissionStatus(permission.getPermissionStatus());
        
        return permissionRepository.save(existingPermission);
    }

    @Override
    @Transactional
    public void deletePermission(Integer permissionId) {
        SysPermission permission = getPermissionById(permissionId);
        
        // 检查是否有子权限
        if (!permission.getChildren().isEmpty()) {
            throw new BusinessException("该权限存在子权限，不能删除");
        }
        
        permissionRepository.deleteById(permissionId);
    }

    @Override
    public List<SysPermission> getChildrenPermissions(Integer parentId) {
        return permissionRepository.findByParentPermissionId(parentId);
    }
} 