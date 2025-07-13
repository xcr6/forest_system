package com.forest.repository;

import com.forest.entity.ForestParcel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ForestParcelRepository extends JpaRepository<ForestParcel, Integer> {
    boolean existsByParcelCode(String parcelCode);

    @Query("SELECT p FROM ForestParcel p WHERE " +
           "(:parcelName IS NULL OR p.parcelName LIKE %:parcelName%) AND " +
           "(:usetypeid IS NULL OR p.landUseType.useTypeId = :usetypeid)")
    Page<ForestParcel> findByConditions(
            @Param("parcelName") String parcelName,
            @Param("usetypeid") Integer usetypeid,
            Pageable pageable
    );
} 