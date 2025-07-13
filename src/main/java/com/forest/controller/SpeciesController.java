package com.forest.controller;

import com.forest.common.api.ApiResponse;
import com.forest.dto.species.SpeciesDTO;
import com.forest.dto.species.SpeciesQueryRequest;
import com.forest.service.TreeSpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {

    @Autowired
    private TreeSpeciesService speciesService;

    // 管理员/资源管理员分页筛选
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPER')")
    public ApiResponse<Page<SpeciesDTO>> getSpeciesList(SpeciesQueryRequest queryRequest, Pageable pageable) {
        return ApiResponse.success(speciesService.getSpecies(queryRequest, pageable));
    }

    // 普通用户无分页筛选，只能查全部
    @GetMapping("/listAll")
    @PreAuthorize("hasRole('ROLE_VIEW')")
    public ApiResponse<List<SpeciesDTO>> getAllSpecies() {
        return ApiResponse.success(speciesService.getSpecies(new SpeciesQueryRequest(), Pageable.unpaged()).getContent());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SPECIES_READ')")
    public ApiResponse<SpeciesDTO> getSpecies(@PathVariable("id") Integer speciesId) {
        return ApiResponse.success(speciesService.getSpeciesById(speciesId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SPECIES_CREATE')")
    public ApiResponse<SpeciesDTO> createSpecies(@Valid @RequestBody SpeciesDTO speciesDTO) {
        return ApiResponse.success(speciesService.createSpecies(speciesDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SPECIES_UPDATE')")
    public ApiResponse<SpeciesDTO> updateSpecies(
            @PathVariable("id") Integer speciesId,
            @Valid @RequestBody SpeciesDTO speciesDTO) {
        return ApiResponse.success(speciesService.updateSpecies(speciesId, speciesDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SPECIES_DELETE')")
    public ApiResponse<Void> deleteSpecies(@PathVariable("id") Integer speciesId) {
        speciesService.deleteSpecies(speciesId);
        return ApiResponse.success();
    }
} 