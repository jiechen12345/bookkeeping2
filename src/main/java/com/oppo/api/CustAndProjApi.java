package com.oppo.api;

import com.oppo.Entity.Customer;
import com.oppo.Entity.Project;
import com.oppo.business.BookService;
import com.oppo.dao.CustomerDao;
import com.oppo.dao.ProjectDao;
import com.oppo.dto.BookPage;
import com.oppo.dto.ProjectDto;
import com.oppo.request.BookReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JieChen on 2019/1/15.
 */
@Controller
public class CustAndProjApi {
    Logger LOGGER = LoggerFactory.getLogger(CustAndProjApi.class);
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private BookService bookService;
    Integer[] pageSizeOption = {5, 10, 15, 20};
    BookReq bookReq = new BookReq();

    //查詢分頁會員列表及修改pageSize
    @GetMapping("/custAndProj")
    public String findAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                          @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                          Model model) {

        return "custAndProj/list";
    }


}
