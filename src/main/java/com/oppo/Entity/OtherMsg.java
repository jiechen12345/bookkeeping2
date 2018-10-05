package com.oppo.Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jiechen on 2018/8/2.
 */
@Entity
public class OtherMsg {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    /**
     * 標題
     */
    private String title;

    /**
     * 點擊數
     */
    private Integer ctr;
    /**
     * editor內容
     */
    private String editor;
    /**
     * 上架日期
     */
    private Date onShelfDate;
    /**
     * 下架日期
     */
    private Date offShelfDate;
    /**
     * 最新更新人員
     */
    private String updatedBy;
    /**
     * 最後更新時間
     */
    private Date updatedAt;
    /**
     * 新增人員
     */
    private String createdBy;
    /**
     * 新增時間
     */
    private Date createdAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }


    public Integer getCtr() {
        return ctr;
    }

    public void setCtr(Integer ctr) {
        this.ctr = ctr;
    }

    public Date getOnShelfDate() {
        return onShelfDate;
    }

    public void setOnShelfDate(Date onShelfDate) {
        this.onShelfDate = onShelfDate;
    }

    public Date getOffShelfDate() {
        return offShelfDate;
    }

    public void setOffShelfDate(Date offShelfDate) {
        this.offShelfDate = offShelfDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OtherMsgReq{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", ctr='" + ctr + '\'' +
                ", editor='" + editor + '\'' +
                ", onShelfDate=" + onShelfDate +
                ", offShelfDate=" + offShelfDate +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
