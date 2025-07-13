package com.forest.repository;

import com.forest.entity.LandUseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LandUseTypeRepository extends JpaRepository<LandUseType, Integer> {
    boolean existsByUseTypeCode(String useTypeCode);

    @Query("SELECT t FROM LandUseType t WHERE " +
           "(:useTypeName IS NULL OR t.useTypeName LIKE %:useTypeName%) AND " +
           "(:useTypeCode IS NULL OR t.useTypeCode LIKE %:useTypeCode%)")
    Page<LandUseType> findByConditions(
            @Param("useTypeName") String useTypeName,
            @Param("useTypeCode") String useTypeCode,
            Pageable pageable
    );
} 