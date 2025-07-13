package com.forest.controller;

import com.forest.common.api.ApiResponse;
import com.forest.dto.usetype.UseTypeDTO;
import com.forest.dto.usetype.UseTypeQueryRequest;
import com.forest.service.LandUseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/landUseType")
public class LandUseTypeController {

    @Autowired
    private LandUseTypeService useTypeService;

    // 管理员/资源管理员分页筛选
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPER')")
    public ApiResponse<Page<UseTypeDTO>> getUseTypeList(UseTypeQueryRequest queryRequest, Pageable pageable) {
        return ApiResponse.success(useTypeService.getUseTypes(queryRequest, pageable));
    }

    // 普通用户无分页筛选，只能查全部
    @GetMapping("/listAll")
    @PreAuthorize("hasRole('ROLE_VIEW')")
    public ApiResponse<List<UseTypeDTO>> getAllUseTypes() {
        return ApiResponse.success(useTypeService.getUseTypes(new UseTypeQueryRequest(), Pageable.unpaged()).getContent());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USETYPE_READ')")
    public ApiResponse<UseTypeDTO> getUseType(@PathVariable("id") Integer useTypeId) {
        return ApiResponse.success(useTypeService.getUseTypeById(useTypeId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USETYPE_CREATE')")
    public ApiResponse<UseTypeDTO> createUseType(@Valid @RequestBody UseTypeDTO useTypeDTO) {
        return ApiResponse.success(useTypeService.createUseType(useTypeDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USETYPE_UPDATE')")
    public ApiResponse<UseTypeDTO> updateUseType(
            @PathVariable("id") Integer useTypeId,
            @Valid @RequestBody UseTypeDTO useTypeDTO) {
        return ApiResponse.success(useTypeService.updateUseType(useTypeId, useTypeDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USETYPE_DELETE')")
    public ApiResponse<Void> deleteUseType(@PathVariable("id") Integer useTypeId) {
        useTypeService.deleteUseType(useTypeId);
        return ApiResponse.success();
    }
} 