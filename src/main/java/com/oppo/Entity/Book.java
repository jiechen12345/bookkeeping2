package com.oppo.Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by JieChen on 2018/10/2.
 */
@Entity
public class Book {
    //流水號201810030001
    @Id
    @Column(length = 12)
    private String id;
    //收支 收1支0
    @Column(length = 1)
    private String incomeOrExpend;
    //是否廠商發票
    private Boolean invoice = false;
    //發票月份
    @Column(length = 7)
    private String invYM;
    //發票號碼
    @Column(length = 10)
    private String invNo;
    //付款與否
    private Boolean paid = false;
    //付款日期
    private Date paidDat;
    //金額
    private Integer amt;
    //建立日期
    private Date createDat;
    //更新日期
    private Date updateDat;

    //建立成員
    @JoinColumn(name="createMember_ID_FK")
    @ManyToOne(targetEntity = Member.class)
    private Member createMember;

    //更新成員
    @JoinColumn(name="updateMember_ID_FK")
    @ManyToOne(targetEntity = Member.class)
    private Member updateMember;

    //專案名稱
    @ManyToOne(targetEntity = Project.class)
    private Project project;

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

    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

    public String getIncomeOrExpend() {
        return incomeOrExpend;
    }

    public void setIncomeOrExpend(String incomeOrExpend) {
        this.incomeOrExpend = incomeOrExpend;
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

    public Member getCreateMember() {
        return createMember;
    }

    public void setCreateMember(Member createMember) {
        this.createMember = createMember;
    }

    public Member getUpdateMember() {
        return updateMember;
    }

    public void setUpdateMember(Member updateMember) {
        this.updateMember = updateMember;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", incomeOrExpend='" + incomeOrExpend + '\'' +
                ", invoice=" + invoice +
                ", invYM='" + invYM + '\'' +
                ", invNo='" + invNo + '\'' +
                ", paid=" + paid +
                ", paidDat=" + paidDat +
                ", amt=" + amt +
                ", createDat=" + createDat +
                ", updateDat=" + updateDat +
                ", createMember=" + createMember +
                ", updateMember=" + updateMember +
                ", project=" + project +
                ", description='" + description + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
