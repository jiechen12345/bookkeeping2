package com.oppo.request;

/**
 * Created by JieChen on 2019/1/16.
 */
public class CustReq {
    public CustReq() {
    }

    public CustReq(String name) {
        this.name = name;
    }

    private Integer id;
    private String name;

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

    @Override
    public String toString() {
        return "CustReq{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
