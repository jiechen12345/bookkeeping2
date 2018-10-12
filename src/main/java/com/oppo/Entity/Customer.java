package com.oppo.Entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JieChen on 2018/10/5.
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String custNm;
    @OneToMany(cascade=CascadeType.MERGE, mappedBy="customer")
    private List<Project> projects;

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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", custNm='" + custNm + '\'' +
                ", projects=" + projects +
                '}';
    }
}
