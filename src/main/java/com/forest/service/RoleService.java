package com.forest.service;

import com.forest.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Page<SysRole> getRoles(Pageable pageable);
    
    SysRole getRoleById(Integer roleId);
    
    SysRole createRole(SysRole role);
    
    SysRole updateRole(Integer roleId, SysRole role);
    
    void deleteRole(Integer roleId);
    
    void assignPermissions(Integer roleId, Set<Integer> permissionIds);
    
    List<SysRole> getAllRoles();
} 