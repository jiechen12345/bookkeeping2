package com.oppo.dto;

/**
 * Created by JieChen on 2019/1/16.
 */
public class CustDto {
    private Integer id;
    private String custNm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustNm() {
        return custNm;
    }

    public void setCustNm(String custNm) {
        this.custNm = custNm;
    }

    @Override
    public String toString() {
        return "CustDto{" +
                "id=" + id +
                ", custNm='" + custNm + '\'' +
                '}';
    }
}
