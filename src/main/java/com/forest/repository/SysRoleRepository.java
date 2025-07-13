package com.forest.repository;

import com.forest.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, Integer> {
    Optional<SysRole> findByRoleCode(String roleCode);
} 