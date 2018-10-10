package com.oppo.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by JieChen on 2018/10/2.
 */
public class BookDto {
    public BookDto(){}

    public BookDto(String id, String incomeOrExpend, Boolean invoice, String invYM, String invNo, Boolean paid, Date paidDat, Integer amt, Integer customerId, String customerNm, Integer projectId, String projectName, Date createDat, Date updateDat, Integer createMemberId, String createMemberName, Integer updateMemberId, String updateMemberName, String description, String remarks) {
        this.id = id;
        this.incomeOrExpend = incomeOrExpend;
        this.invoice = invoice;
        this.invYM = invYM;
        this.invNo = invNo;
        this.paid = paid;
        this.paidDat = paidDat;
        this.amt = amt;
        this.customerId = customerId;
        this.customerNm = customerNm;
        this.projectId = projectId;
        this.projectName = projectName;
        this.createDat = createDat;
        this.updateDat = updateDat;
        this.createMemberId = createMemberId;
        this.createMemberName = createMemberName;
        this.updateMemberId = updateMemberId;
        this.updateMemberName = updateMemberName;
        this.description = description;
        this.remarks = remarks;
    }

    private String id;
    //收支 收1支0
    private String incomeOrExpend;
    //是否廠商發票
    private Boolean invoice = false;
    //發票月份
    private String invYM;
    //發票號碼
    private String invNo;
    //付款與否
    private Boolean paid = false;
    //付款日期
    private Date paidDat;
    //金額
    private Integer amt;
    //客戶ID
    private Integer customerId;
    //客戶名稱
    private String customerNm;
    //專案名稱
    private Integer projectId;
    //專案名稱
    private String projectName;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getInvoice() {
        return invoice;
    }

    public void setInvoice(Boolean invoice) {
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

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNm() {
        return customerNm;
    }

    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm;
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

    public void setCreateMemberId(Integer createMemberId) {
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

    @Override
    public String toString() {
        return "BookDto{" +
                "id='" + id + '\'' +
                ", incomeOrExpend='" + incomeOrExpend + '\'' +
                ", invoice=" + invoice +
                ", invYM='" + invYM + '\'' +
                ", invNo='" + invNo + '\'' +
                ", paid=" + paid +
                ", paidDat=" + paidDat +
                ", amt=" + amt +
                ", customerId=" + customerId +
                ", customerNm='" + customerNm + '\'' +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
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
