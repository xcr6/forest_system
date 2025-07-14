package com.forest.controller;

import com.forest.common.api.ApiResponse;
import com.forest.dto.parcel.ParcelDTO;
import com.forest.dto.parcel.ParcelQueryRequest;
import com.forest.service.ForestParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/parcel")
public class ParcelController {

    @Autowired
    private ForestParcelService parcelService;

    // 管理员/资源管理员分页筛选
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPER')")
    public ApiResponse<Page<ParcelDTO>> getParcelList(ParcelQueryRequest queryRequest, Pageable pageable) {
        return ApiResponse.success(parcelService.getParcels(queryRequest, pageable));
    }

    // 普通用户无分页筛选，只能查全部
    @GetMapping("/listAll")
    @PreAuthorize("hasRole('ROLE_VIEW')")
    public ApiResponse<List<ParcelDTO>> getAllParcels() {
        return ApiResponse.success(parcelService.getParcels(new ParcelQueryRequest(), Pageable.unpaged()).getContent());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PARCEL_READ')")
    public ApiResponse<ParcelDTO> getParcel(@PathVariable("id") Integer parcelId) {
        return ApiResponse.success(parcelService.getParcelById(parcelId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('PARCEL_CREATE')")
    public ApiResponse<ParcelDTO> createParcel(@Valid @RequestBody ParcelDTO parcelDTO) {
        return ApiResponse.success(parcelService.createParcel(parcelDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PARCEL_UPDATE')")
    public ApiResponse<ParcelDTO> updateParcel(
            @PathVariable("id") Integer parcelId,
            @Valid @RequestBody ParcelDTO parcelDTO) {
        return ApiResponse.success(parcelService.updateParcel(parcelId, parcelDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PARCEL_DELETE')")
    public ApiResponse<Void> deleteParcel(@PathVariable("id") Integer parcelId) {
        parcelService.deleteParcel(parcelId);
        return ApiResponse.success();
    }
} 