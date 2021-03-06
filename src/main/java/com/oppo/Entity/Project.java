package com.oppo.Entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JieChen on 2018/10/3.
 */
@Entity
public class Project {
    public Project() {
    }

    public Project(String projectName, Customer customer) {
        this.projectName = projectName;
        this.customer = customer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String projectName;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CUSTOMER_ID_FK")
    private Customer customer;
    private Integer deleted = 0;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        projectName = projectName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", customer=" + customer +
                ", deleted=" + deleted +
                '}';
    }
}
