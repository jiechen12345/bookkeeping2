package com.oppo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.oppo.Entity.Project;
import com.oppo.business.BookService;
import com.oppo.dao.CustomerDao;
import com.oppo.dao.ProjectDao;
import com.oppo.dto.ProjectDto;
import com.oppo.request.BookReq;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JieChen on 2018/10/5.
 */
@RestController
@RequestMapping(value = "/books", produces = "application/json")
public class BookAjaxApi {
    Logger LOGGER = LoggerFactory.getLogger(BookAjaxApi.class);
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private BookService bookService;
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void creat(@RequestBody BookReq bookReq) {
        System.out.println(bookReq.toString());
        bookService.create(bookReq);
//        JSONArray jsArr = JSONArray.fromObject(projects);
//        return jsArr;
    }

    @RequestMapping(value = "/findProjectByCustomerId", method = RequestMethod.POST)
    public List<ProjectDto> findProjectByCustomerId(@RequestBody Integer customerId) {
        List<Project> projects = projectDao.findByCustomer_Id(customerId);
        List<ProjectDto> projectDtos = projectDao.findByCustomer_Id(customerId).stream()
                .map(this::getProjectDto)
                .collect(Collectors.toList());
        return projectDtos;
//        JSONArray jsArr = JSONArray.fromObject(projects);
//        return jsArr;
    }
    private ProjectDto getProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setProjectName(project.getProjectName());
        projectDto.setCustomerId(project.getCustomer().getId());
        return projectDto;
    }

}
