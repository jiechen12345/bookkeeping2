package com.oppo.Entity;
import javax.persistence.*;

import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JieChen on 2018/7/26.
 */
@Entity
public class Departemt {
    @Id
    private Integer id;

    private String depName;

    @OneToMany(targetEntity = Member.class, mappedBy = "departemt")
    private List<Member> members = new LinkedList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public List<Member> getTeams() {
        return members;
    }

    public void setTeams(List<Member> teams) {
        this.members = teams;
    }

    @Override
    public String toString() {
        return "Departemt{" +
                "id=" + id +
                ", depName='" + depName + '\'' +
                ", members=" + members +
                '}';
    }
}
