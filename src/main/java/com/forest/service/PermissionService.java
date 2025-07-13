package com.forest.service;

import com.forest.entity.SysPermission;

import java.util.List;

public interface PermissionService {
    List<SysPermission> getPermissionTree();
    
    SysPermission getPermissionById(Integer permissionId);
    
    SysPermission createPermission(SysPermission permission);
    
    SysPermission updatePermission(Integer permissionId, SysPermission permission);
    
    void deletePermission(Integer permissionId);
    
    List<SysPermission> getChildrenPermissions(Integer parentId);
} 