package com.forest.dto.policy;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PolicyDTO {
    private Integer policyId;

    @NotBlank(message = "政策编码不能为空")
    private String policyCode;

    @NotBlank(message = "政策标题不能为空")
    private String title;

    @NotBlank(message = "政策内容不能为空")
    private String content;

    private String category;
    private String level;

    @NotNull(message = "发布日期不能为空")
    private Date publishDate;

    @NotNull(message = "生效日期不能为空")
    private Date effectiveDate;

    private Date expiryDate;
    private String source;
    private String attachmentUrl;
    private Short policyStatus;

    private Integer publisherId;
    private String publisherName;

    private Date createTime;
    private Date updateTime;
    private String remarks;

    public Integer getPolicyId() { return policyId; }
    public void setPolicyId(Integer policyId) { this.policyId = policyId; }
    public String getPolicyCode() { return policyCode; }
    public void setPolicyCode(String policyCode) { this.policyCode = policyCode; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public java.util.Date getPublishDate() { return publishDate; }
    public void setPublishDate(java.util.Date publishDate) { this.publishDate = publishDate; }
    public java.util.Date getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(java.util.Date effectiveDate) { this.effectiveDate = effectiveDate; }
    public java.util.Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(java.util.Date expiryDate) { this.expiryDate = expiryDate; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getAttachmentUrl() { return attachmentUrl; }
    public void setAttachmentUrl(String attachmentUrl) { this.attachmentUrl = attachmentUrl; }
    public Short getPolicyStatus() { return policyStatus; }
    public void setPolicyStatus(Short policyStatus) { this.policyStatus = policyStatus; }
    public Integer getPublisherId() { return publisherId; }
    public void setPublisherId(Integer publisherId) { this.publisherId = publisherId; }
    public String getPublisherName() { return publisherName; }
    public void setPublisherName(String publisherName) { this.publisherName = publisherName; }
    public java.util.Date getCreateTime() { return createTime; }
    public void setCreateTime(java.util.Date createTime) { this.createTime = createTime; }
    public java.util.Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(java.util.Date updateTime) { this.updateTime = updateTime; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
} 