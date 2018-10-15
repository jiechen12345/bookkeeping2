package com.oppo.request;

import java.util.Date;

/**
 * Created by JieChen on 2018/10/3.
 */
public class BookReq {
    public BookReq(String q_id, String q_id2, Integer q_amt, Integer q_amt2, String q_invYM, String q_invYM2, String q_paidDat, String q_paidDat2, String q_incomeOrExpend, String q_invNo, Integer q_customerId, Integer q_projectId, Integer q_invoice, Integer q_paid, String q_description) {
        this.q_id = q_id;
        this.q_id2 = q_id2;
        this.q_amt = q_amt;
        this.q_amt2 = q_amt2;
        this.q_invYM = q_invYM;
        this.q_invYM2 = q_invYM2;
        this.q_paidDat = q_paidDat;
        this.q_paidDat2 = q_paidDat2;
        this.q_incomeOrExpend = q_incomeOrExpend;
        this.q_invNo = q_invNo;
        this.q_customerId = q_customerId;
        this.q_projectId = q_projectId;
        this.q_invoice = q_invoice;
        this.q_paid = q_paid;
        this.q_description = q_description;
    }

    public BookReq() {
    }

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
    private Integer projectId;
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
    //---queryAll
    private String q_id;
    private String q_id2;
    private Integer q_amt;
    private Integer q_amt2;
    private String q_invYM;
    private String q_invYM2;
    private String q_paidDat;
    private String q_paidDat2;
    private String q_incomeOrExpend;
    private String q_invNo;
    private Integer q_customerId;
    private Integer q_projectId;
    private Integer q_invoice;
    private Integer q_paid;
    private String q_description;
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

    public String getQ_id() {
        return q_id;
    }

    public void setQ_id(String q_id) {
        this.q_id = q_id;
    }

    public String getQ_id2() {
        return q_id2;
    }

    public void setQ_id2(String q_id2) {
        this.q_id2 = q_id2;
    }

    public Integer getQ_amt() {
        return q_amt;
    }

    public void setQ_amt(Integer q_amt) {
        this.q_amt = q_amt;
    }

    public Integer getQ_amt2() {
        return q_amt2;
    }

    public void setQ_amt2(Integer q_amt2) {
        this.q_amt2 = q_amt2;
    }

    public String getQ_invYM() {
        return q_invYM;
    }

    public void setQ_invYM(String q_invYM) {
        this.q_invYM = q_invYM;
    }

    public String getQ_invYM2() {
        return q_invYM2;
    }

    public void setQ_invYM2(String q_invYM2) {
        this.q_invYM2 = q_invYM2;
    }

    public String getQ_paidDat() {
        return q_paidDat;
    }

    public void setQ_paidDat(String q_paidDat) {
        this.q_paidDat = q_paidDat;
    }

    public String getQ_paidDat2() {
        return q_paidDat2;
    }

    public void setQ_paidDat2(String q_paidDat2) {
        this.q_paidDat2 = q_paidDat2;
    }

    public String getQ_incomeOrExpend() {
        return q_incomeOrExpend;
    }

    public void setQ_incomeOrExpend(String q_incomeOrExpend) {
        this.q_incomeOrExpend = q_incomeOrExpend;
    }

    public String getQ_invNo() {
        return q_invNo;
    }

    public void setQ_invNo(String q_invNo) {
        this.q_invNo = q_invNo;
    }

    public Integer getQ_customerId() {
        return q_customerId;
    }

    public void setQ_customerId(Integer q_customerId) {
        this.q_customerId = q_customerId;
    }

    public Integer getQ_projectId() {
        return q_projectId;
    }

    public void setQ_projectId(Integer q_projectId) {
        this.q_projectId = q_projectId;
    }

    public Integer getQ_invoice() {
        return q_invoice;
    }

    public void setQ_invoice(Integer q_invoice) {
        this.q_invoice = q_invoice;
    }

    public Integer getQ_paid() {
        return q_paid;
    }

    public void setQ_paid(Integer q_paid) {
        this.q_paid = q_paid;
    }

    public String getQ_description() {
        return q_description;
    }

    public void setQ_description(String q_description) {
        this.q_description = q_description;
    }

    @Override
    public String toString() {
        return "BookReq{" +
                "id='" + id + '\'' +
                ", incomeOrExpend='" + incomeOrExpend + '\'' +
                ", invoice=" + invoice +
                ", invYM='" + invYM + '\'' +
                ", invNo='" + invNo + '\'' +
                ", paid=" + paid +
                ", paidDat=" + paidDat +
                ", amt=" + amt +
                ", projectName='" + projectName + '\'' +
                ", projectId=" + projectId +
                ", customerId=" + customerId +
                ", createDat=" + createDat +
                ", updateDat=" + updateDat +
                ", createMemberId=" + createMemberId +
                ", createMemberName='" + createMemberName + '\'' +
                ", updateMemberId=" + updateMemberId +
                ", updateMemberName='" + updateMemberName + '\'' +
                ", description='" + description + '\'' +
                ", remarks='" + remarks + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String queryAll() {
        return "BookReq{" +
                "q_id='" + q_id + '\'' +
                ", q_id2='" + q_id2 + '\'' +
                ", q_amt=" + q_amt +
                ", q_amt2=" + q_amt2 +
                ", q_invYM='" + q_invYM + '\'' +
                ", q_invYM2='" + q_invYM2 + '\'' +
                ", q_paidDat=" + q_paidDat +
                ", q_paidDat2=" + q_paidDat2 +
                ", q_incomeOrExpend='" + q_incomeOrExpend + '\'' +
                ", q_invNo='" + q_invNo + '\'' +
                ", q_customerId=" + q_customerId +
                ", q_projectId=" + q_projectId +
                ", q_invoice=" + q_invoice +
                ", q_paid=" + q_paid +
                ", q_description='" + q_description + '\'' +
                '}';
    }
}
