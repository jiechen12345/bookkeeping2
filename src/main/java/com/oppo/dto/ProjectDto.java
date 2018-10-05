package com.oppo.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JieChen on 2018/10/5.
 */
public class ProjectDto {
    public ProjectDto() {
    }

    public ProjectDto(Integer id, String projectName, Integer customerId) {
        this.id = id;
        ProjectName = projectName;
        this.customerId = customerId;
    }

    public ProjectDto(Integer id, String projectName) {
        this.id = id;
        ProjectName = projectName;
    }

    private Integer id;

    private String ProjectName;

    public Integer getId() {
        return id;
    }
    private Integer customerId ;
    @Override
    public String toString() {
        return "ProjectDto{" +
                "id=" + id +
                ", ProjectName='" + ProjectName + '\'' +
                '}';
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
