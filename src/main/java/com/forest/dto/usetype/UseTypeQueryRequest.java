package com.forest.dto.usetype;

import lombok.Data;

@Data
public class UseTypeQueryRequest {
    private String useTypeName;
    private String useTypeCode;

    public String getUseTypeName() { return useTypeName; }
    public void setUseTypeName(String useTypeName) { this.useTypeName = useTypeName; }
    public String getUseTypeCode() { return useTypeCode; }
    public void setUseTypeCode(String useTypeCode) { this.useTypeCode = useTypeCode; }
} 