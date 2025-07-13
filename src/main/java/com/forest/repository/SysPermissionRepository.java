package com.forest.repository;

import com.forest.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysPermissionRepository extends JpaRepository<SysPermission, Integer> {
    Optional<SysPermission> findByPermissionCode(String permissionCode);

    @Query("SELECT p FROM SysPermission p WHERE p.parent IS NULL")
    List<SysPermission> findAllRootPermissions();

    List<SysPermission> findByParentPermissionId(Integer parentId);
} 