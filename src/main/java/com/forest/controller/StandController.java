package com.forest.controller;

import com.forest.common.api.ApiResponse;
import com.forest.dto.stand.StandDTO;
import com.forest.dto.stand.StandQueryRequest;
import com.forest.service.ForestStandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stand")
public class StandController {

    @Autowired
    private ForestStandService standService;

    // 管理员/资源管理员分页筛选
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPER')")
    public ApiResponse<Page<StandDTO>> getStandList(StandQueryRequest queryRequest, Pageable pageable) {
        return ApiResponse.success(standService.getStands(queryRequest, pageable));
    }

    // 普通用户无分页筛选，只能查全部
    @GetMapping("/listAll")
    @PreAuthorize("hasRole('ROLE_VIEW')")
    public ApiResponse<List<StandDTO>> getAllStands() {
        return ApiResponse.success(standService.getStands(new StandQueryRequest(), Pageable.unpaged()).getContent());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('STAND_READ')")
    public ApiResponse<StandDTO> getStand(@PathVariable("id") Integer standId) {
        return ApiResponse.success(standService.getStandById(standId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('STAND_CREATE')")
    public ApiResponse<StandDTO> createStand(@Valid @RequestBody StandDTO standDTO) {
        return ApiResponse.success(standService.createStand(standDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('STAND_UPDATE')")
    public ApiResponse<StandDTO> updateStand(
            @PathVariable("id") Integer standId,
            @Valid @RequestBody StandDTO standDTO) {
        return ApiResponse.success(standService.updateStand(standId, standDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('STAND_DELETE')")
    public ApiResponse<Void> deleteStand(@PathVariable("id") Integer standId) {
        standService.deleteStand(standId);
        return ApiResponse.success();
    }
} 