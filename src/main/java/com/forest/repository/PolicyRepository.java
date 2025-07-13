package com.forest.repository;

import com.forest.entity.Policy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {
    Optional<Policy> findByPolicyCode(String policyCode);

    boolean existsByPolicyCode(String policyCode);

    @Query("SELECT p FROM Policy p WHERE " +
           "(:policyCode IS NULL OR p.policyCode = :policyCode) AND " +
           "(:title IS NULL OR p.title LIKE %:title%) AND " +
           "(:category IS NULL OR p.category = :category) AND " +
           "(:level IS NULL OR p.level = :level) AND " +
           "(:publishDateStart IS NULL OR p.publishDate >= :publishDateStart) AND " +
           "(:publishDateEnd IS NULL OR p.publishDate <= :publishDateEnd) AND " +
           "(:effectiveDateStart IS NULL OR p.effectiveDate >= :effectiveDateStart) AND " +
           "(:effectiveDateEnd IS NULL OR p.effectiveDate <= :effectiveDateEnd) AND " +
           "(:source IS NULL OR p.source = :source) AND " +
           "(:policyStatus IS NULL OR p.policyStatus = :policyStatus) AND " +
           "(:publisherId IS NULL OR p.publisher.id = :publisherId)")
    Page<Policy> findByConditions(
            @Param("policyCode") String policyCode,
            @Param("title") String title,
            @Param("category") String category,
            @Param("level") String level,
            @Param("publishDateStart") Date publishDateStart,
            @Param("publishDateEnd") Date publishDateEnd,
            @Param("effectiveDateStart") Date effectiveDateStart,
            @Param("effectiveDateEnd") Date effectiveDateEnd,
            @Param("source") String source,
            @Param("policyStatus") Short policyStatus,
            @Param("publisherId") Integer publisherId,
            Pageable pageable
    );

    Page<Policy> findByPolicyStatus(Short status, Pageable pageable);

    Page<Policy> findByCategory(String category, Pageable pageable);

    Page<Policy> findByLevel(String level, Pageable pageable);
} 