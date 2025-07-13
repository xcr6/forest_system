package com.forest.dto.usetype;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class UseTypeDTO {
    private Integer useTypeId;

    @NotBlank(message = "土地利用类型编码不能为空")
    private String useTypeCode;

    @NotBlank(message = "土地利用类型名称不能为空")
    private String useTypeName;

    private String description;
    private String remarks;

    public Integer getUseTypeId() { return useTypeId; }
    public void setUseTypeId(Integer useTypeId) { this.useTypeId = useTypeId; }
    public String getUseTypeCode() { return useTypeCode; }
    public void setUseTypeCode(String useTypeCode) { this.useTypeCode = useTypeCode; }
    public String getUseTypeName() { return useTypeName; }
    public void setUseTypeName(String useTypeName) { this.useTypeName = useTypeName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
} 