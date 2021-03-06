package com.oppo.Entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JieChen on 2018/10/5.
 */
@Entity
public class Customer {
    public Customer(String custNm) {
        this.custNm = custNm;
    }

    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String custNm;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "customer")
    private List<Project> projects;
    private Integer deleted = 0;

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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", custNm='" + custNm + '\'' +
                ", projects=" + projects +
                ", deleted=" + deleted +
                '}';
    }
}
