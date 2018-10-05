package com.oppo.api;

import com.oppo.Entity.Customer;
import com.oppo.Entity.Departemt;
import com.oppo.Entity.Project;
import com.oppo.business.BookService;
import com.oppo.dao.CustomerDao;
import com.oppo.dao.ProjectDao;
import com.oppo.dto.BookPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by JieChen on 2018/10/2.
 */
@Controller
public class BookApi {
    Logger LOGGER = LoggerFactory.getLogger(BookApi.class);
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private BookService bookService;

    //查詢分頁會員列表及修改pageSize
    @GetMapping("/books")
    public String changePageSize(@RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "5")Integer pageSize ,Model model) {
        BookPage bookPage = bookService.getAllForm(page,pageSize);
        //List<MemberDto> memberDtoList = memberService.findAll();
        List<Customer> customers = customerDao.findAll();
        model.addAttribute("books", bookPage.getContents());
        model.addAttribute("customers", customers);
        model.addAttribute("indexPage", bookPage.getCurrentPage());
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("count", bookPage.getCount());
        return "book/list";
    }
}
