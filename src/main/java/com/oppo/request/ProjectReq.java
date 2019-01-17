package com.oppo.request;

/**
 * Created by JieChen on 2019/1/17.
 */
public class ProjectReq {
    private Integer id;
    private String name;
    private Integer custId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    @Override
    public String toString() {
        return "ProjectReq{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", custId='" + custId + '\'' +
                '}';
    }
}
