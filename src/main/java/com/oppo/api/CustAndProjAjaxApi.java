package com.oppo.api;

import com.oppo.Entity.Customer;
import com.oppo.Entity.Member;
import com.oppo.Entity.Project;
import com.oppo.dao.CustomerDao;
import com.oppo.dao.ProjectDao;
import com.oppo.dto.CustDto;
import com.oppo.dto.MemberDto;
import com.oppo.dto.ProjectDto;
import com.oppo.request.BookReq;
import com.oppo.request.CustReq;
import com.oppo.request.ProjectReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JieChen on 2019/1/15.
 */
@RestController
@RequestMapping(value = "/custAjax", produces = "application/json")
public class CustAndProjAjaxApi {
    Logger LOGGER = LoggerFactory.getLogger(BookAjaxApi.class);
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(method = RequestMethod.GET, value = "/getLists")
    public List<CustDto> queryAll() {
        List<CustDto> list = customerDao.findAll().stream().filter(it -> it.getDeleted() == null || it.getDeleted() != 1).map(this::getCustomerDto)
                .collect(Collectors.toList());
        return list;
        //bookService.create(bookReq);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/addCust")
    public Boolean addCust(@RequestBody CustReq custReq) {
        Boolean flag = false;
        if (customerDao.countByDeletedNotAndCustNm(1, custReq.getName()) == 0) {
            Customer customer = new Customer(custReq.getName());
            customerDao.save(customer);
            flag = true;
        }
        return flag;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addProj")
    public Boolean addProject(@RequestBody ProjectReq projectReq) {
        Boolean flag = false;
        System.out.println(projectReq.toString());
        Customer customer = customerDao.findById(projectReq.getCustId()).orElse(null);
        if (projectDao.countProjectByProjectNameAndCustomer(projectReq.getName(), customer) == 0) {
            Project project = new Project(projectReq.getName(), customer);
            projectDao.save(project);
            flag = true;
        }
        return flag;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delCust/{id}")
    public void delCust(@PathVariable Integer id) {
        System.out.println(id);
        Customer customer = customerDao.findById(id).orElseGet(null);
        customer.setDeleted(1);
        customerDao.save(customer);
        //不刪掉projects跟 customer前端假裝刪掉就好了 避免關聯出錯
        //List<Project> projectList = projectDao.findByCustomer_Id(id);
        //projectDao.deleteAll(projectList);
        //customerDao.delete(customer);
//        Customer customer = new Customer(custReq.getName());
//        customerDao.save(customer);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delProj/{id}")
    public void delProject(@PathVariable Integer id) {
        System.out.println(id);
        Project project = projectDao.findById(id).orElseGet(null);
        projectDao.delete(project);
//        Customer customer = new Customer(custReq.getName());
//        customerDao.save(customer);
    }

    private CustDto getCustomerDto(Customer cust) {
        CustDto custDto = new CustDto();
        custDto.setId(cust.getId());
        custDto.setCustNm(cust.getCustNm());

        return custDto;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getProjectList/{custId}")
    public List<ProjectDto> queryProjectList(@PathVariable Integer custId) {
        List<ProjectDto> list = projectDao.findByCustomer_Id(custId).stream().map(this::getProjectDto)
                .collect(Collectors.toList());
        return list;
        //bookService.create(bookReq);
    }

    private ProjectDto getProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setProjectName(project.getProjectName());
        return projectDto;
    }
}
