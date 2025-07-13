package com.forest.dto.policy;

import lombok.Data;

import java.util.Date;

@Data
public class PolicyQueryRequest {
    private String policyCode;
    private String title;
    private String category;
    private String level;
    private Date publishDateStart;
    private Date publishDateEnd;
    private Date effectiveDateStart;
    private Date effectiveDateEnd;
    private String source;
    private Short policyStatus;
    private Integer publisherId;
} 