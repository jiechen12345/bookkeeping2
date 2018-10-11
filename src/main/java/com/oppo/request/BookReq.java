package com.oppo.request;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by JieChen on 2018/10/3.
 */
public class BookReq {
    private String id;
    //收支 收1支0
    private String incomeOrExpend;
    //是否廠商發票
    private Integer invoice = 0;
    //發票月份
    private String invYM;
    //發票號碼
    private String invNo;
    //付款與否
    private Integer paid = 0;
    //付款日期
    private Date paidDat;
    //金額
    private Integer amt;
    //專案名稱
    private String projectName;
    //專案名稱
    private String projectId;
    //客戶名稱
    private Integer customerId ;
    //建立日期
    private Date createDat;
    //更新日期
    private Date updateDat;
    //建立成員
    private Integer createMemberId;
    //建立成員
    private String createMemberName;
    //更新成員
    private Integer updateMemberId;
    //更新成員
    private String updateMemberName;
    //說明
    private String description;
    //備註
    private String remarks;
    //CRUD
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public String getInvYM() {
        return invYM;
    }

    public void setInvYM(String invYM) {
        this.invYM = invYM;
    }

    public String getInvNo() {
        return invNo;
    }

    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    public Date getPaidDat() {
        return paidDat;
    }

    public void setPaidDat(Date paidDat) {
        this.paidDat = paidDat;
    }

    public String getIncomeOrExpend() {
        return incomeOrExpend;
    }

    public void setIncomeOrExpend(String incomeOrExpend) {
        this.incomeOrExpend = incomeOrExpend;
    }

    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getCreateDat() {
        return createDat;
    }

    public void setCreateDat(Date createDat) {
        this.createDat = createDat;
    }

    public Date getUpdateDat() {
        return updateDat;
    }

    public void setUpdateDat(Date updateDat) {
        this.updateDat = updateDat;
    }

    public Integer getCreateMemberId() {
        return createMemberId;
    }

    public void setCreatemMmberId(Integer createMemberId) {
        this.createMemberId = createMemberId;
    }

    public String getCreateMemberName() {
        return createMemberName;
    }

    public void setCreateMemberName(String createMemberName) {
        this.createMemberName = createMemberName;
    }

    public Integer getUpdateMemberId() {
        return updateMemberId;
    }

    public void setUpdateMemberId(Integer updateMemberId) {
        this.updateMemberId = updateMemberId;
    }

    public String getUpdateMemberName() {
        return updateMemberName;
    }

    public void setUpdateMemberName(String updateMemberName) {
        this.updateMemberName = updateMemberName;
    }

    public void setCreateMemberId(Integer createMemberId) {
        this.createMemberId = createMemberId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookReq{" +
                "status='" + status + '\'' +
                "*id='" + id + '\'' +
                ", incomeOrExpend='" + incomeOrExpend + '\'' +
                ", invoice=" + invoice +
                ", invYM='" + invYM + '\'' +
                ", invNo='" + invNo + '\'' +
                ", paid=" + paid +
                ", paidDat=" + paidDat +
                ", amt=" + amt +
                ", projectName='" + projectName + '\'' +
                ", projectId='" + projectId + '\'' +
                ", customerId=" + customerId +
                ", createDat=" + createDat +
                ", updateDat=" + updateDat +
                ", createMemberId=" + createMemberId +
                ", createMemberName='" + createMemberName + '\'' +
                ", updateMemberId=" + updateMemberId +
                ", updateMemberName='" + updateMemberName + '\'' +
                ", description='" + description + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
