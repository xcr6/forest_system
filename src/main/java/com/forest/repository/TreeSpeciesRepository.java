package com.forest.repository;

import com.forest.entity.TreeSpecies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TreeSpeciesRepository extends JpaRepository<TreeSpecies, Integer> {
    boolean existsBySpeciesCode(String speciesCode);

    @Query("SELECT s FROM TreeSpecies s WHERE " +
           "(:speciesName IS NULL OR s.speciesName LIKE %:speciesName%) AND " +
           "(:speciesCode IS NULL OR s.speciesCode LIKE %:speciesCode%)")
    Page<TreeSpecies> findByConditions(
            @Param("speciesName") String speciesName,
            @Param("speciesCode") String speciesCode,
            Pageable pageable
    );
} 