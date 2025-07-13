package com.forest.repository;

import com.forest.entity.ForestStand;
import com.forest.entity.StandSpecies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StandSpeciesRepository extends JpaRepository<StandSpecies, Integer> {
    @Modifying
    @Query("DELETE FROM StandSpecies ss WHERE ss.stand = :stand")
    void deleteByStand(@Param("stand") ForestStand stand);
} 