package com.oppo.dto.pdf;


/**
 * Created by JieChen on 2018/10/2.
 */
public class BookPdfDto {
    public BookPdfDto() {
    }

    private String id;
    //收支 收1支0
    private String incomeOrExpend;
    //是否廠商發票
    private String invoice;
    //發票月份
    private String invYM;
    //發票號碼
    private String invNo;
    //付款與否
    private String paid;
    //付款日期
    private String paidDat;
    //金額
    private String amt;
    //客戶ID
    private String customerId;
    //客戶名稱
    private String customerNm;
    //專案名稱
    private String projectId;
    //專案名稱
    private String projectName;
    //建立日期
    private String createDat;
    //更新日期
    private String updateDat;
    //建立成員
    private String createMemberId;
    //建立成員
    private String createMemberName;
    //更新成員
    private String updateMemberId;
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

    public String getIncomeOrExpend() {
        return incomeOrExpend;
    }

    public void setIncomeOrExpend(String incomeOrExpend) {
        this.incomeOrExpend = incomeOrExpend;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
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

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getPaidDat() {
        return paidDat;
    }

    public void setPaidDat(String paidDat) {
        this.paidDat = paidDat;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNm() {
        return customerNm;
    }

    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreateDat() {
        return createDat;
    }

    public void setCreateDat(String createDat) {
        this.createDat = createDat;
    }

    public String getUpdateDat() {
        return updateDat;
    }

    public void setUpdateDat(String updateDat) {
        this.updateDat = updateDat;
    }

    public String getCreateMemberId() {
        return createMemberId;
    }

    public void setCreateMemberId(String createMemberId) {
        this.createMemberId = createMemberId;
    }

    public String getCreateMemberName() {
        return createMemberName;
    }

    public void setCreateMemberName(String createMemberName) {
        this.createMemberName = createMemberName;
    }

    public String getUpdateMemberId() {
        return updateMemberId;
    }

    public void setUpdateMemberId(String updateMemberId) {
        this.updateMemberId = updateMemberId;
    }

    public String getUpdateMemberName() {
        return updateMemberName;
    }

    public void setUpdateMemberName(String updateMemberName) {
        this.updateMemberName = updateMemberName;
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

    @Override
    public String toString() {
        return "BookPdfDto{" +
                "id='" + id + '\'' +
                ", incomeOrExpend='" + incomeOrExpend + '\'' +
                ", invoice='" + invoice + '\'' +
                ", invYM='" + invYM + '\'' +
                ", invNo='" + invNo + '\'' +
                ", paid='" + paid + '\'' +
                ", paidDat='" + paidDat + '\'' +
                ", amt='" + amt + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerNm='" + customerNm + '\'' +
                ", projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", createDat='" + createDat + '\'' +
                ", updateDat='" + updateDat + '\'' +
                ", createMemberId='" + createMemberId + '\'' +
                ", createMemberName='" + createMemberName + '\'' +
                ", updateMemberId='" + updateMemberId + '\'' +
                ", updateMemberName='" + updateMemberName + '\'' +
                ", description='" + description + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
