package com.oppo.Entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JieChen on 2018/10/3.
 */
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String ProjectName;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="CUSTOMER_ID_FK")
    private Customer customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", ProjectName='" + ProjectName + '\'' +
                ", customer=" + customer +
                '}';
    }
}
