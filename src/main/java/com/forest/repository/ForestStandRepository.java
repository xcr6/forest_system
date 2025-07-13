package com.forest.repository;

import com.forest.entity.ForestStand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ForestStandRepository extends JpaRepository<ForestStand, Integer> {
    boolean existsByStandCode(String standCode);

    @Query("SELECT s FROM ForestStand s WHERE " +
           "(:standName IS NULL OR s.standName LIKE %:standName%) AND " +
           "(:parcelId IS NULL OR s.parcel.parcelId = :parcelId) AND " +
           "(:aspect IS NULL OR s.parcel.aspect = :aspect)")
    Page<ForestStand> findByConditions(
            @Param("standName") String standName,
            @Param("parcelId") Integer parcelId,
            @Param("aspect") String aspect,
            Pageable pageable
    );
} 