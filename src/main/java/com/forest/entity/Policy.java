package com.forest.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_policy")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id")
    private Integer policyId;

    @Column(name = "policy_code", nullable = false, unique = true)
    private String policyCode;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "category")
    private String category;  // 政策分类：林业政策、环保政策等

    @Column(name = "level")
    private String level;  // 政策级别：国家、省、市、县

    @Column(name = "publish_date")
    private Date publishDate;  // 发布日期

    @Column(name = "effective_date")
    private Date effectiveDate;  // 生效日期

    @Column(name = "expiry_date")
    private Date expiryDate;  // 失效日期

    @Column(name = "source")
    private String source;  // 政策来源：发布机构

    @Column(name = "attachment_url")
    private String attachmentUrl;  // 附件URL

    @Column(name = "policy_status")
    private Short policyStatus;  // 状态：0-草稿，1-已发布，2-已失效

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private SysUser publisher;  // 发布人

    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "remarks")
    private String remarks;
} 