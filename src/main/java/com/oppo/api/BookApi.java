package com.oppo.api;

import com.oppo.Entity.Customer;
import com.oppo.Entity.Departemt;
import com.oppo.Entity.Project;
import com.oppo.business.BookService;
import com.oppo.common.PdfUtils;
import com.oppo.dao.CustomerDao;
import com.oppo.dao.ProjectDao;
import com.oppo.dto.BookPage;
import com.oppo.dto.MemberDto;
import com.oppo.dto.ProjectDto;
import com.oppo.request.BookReq;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;
import static org.thymeleaf.templatemode.TemplateMode.HTML;

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
    Integer[] pageSizeOption = {5, 10, 15, 20};
    BookReq bookReq = new BookReq();
    //------------PDF
    private static final String OUTPUT_FILE = "123123.pdf";
    private static final String UTF_8 = "UTF-8";

    //查詢分頁會員列表及修改pageSize
    @GetMapping("/books")
    public String findAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                          @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                          Model model) {
        //BookReq bookReq = new BookReq(q_id, q_id2, q_amt, q_amt2, q_invYM, q_invYM2, q_paidDat, q_paidDat2, q_incomeOrExpend, q_invNo, q_customerId, q_projectId, q_invoice, q_paid, q_description);
        String nowYM = new SimpleDateFormat("yyyyMM").format(new Date());
        bookReq = new BookReq();
        LOGGER.info("findAll.page= " + page);
        LOGGER.info("findAll.pageSize= " + pageSize);
        BookPage bookPage = bookService.getAllForm(page, pageSize, nowYM);
        List<ProjectDto> projectDtos = null; //for查詢用的ProjectDtoList
        //傳回query 參數
        if (bookReq.getQ_customerId() != null && bookReq.getQ_customerId() != 0) {
            projectDtos = this.findProjectByCustomerId(bookReq.getQ_customerId());
        }
        List<Customer> customers = customerDao.findAll().stream().filter(it -> it.getDeleted() == null || it.getDeleted() != 1).collect(Collectors.toList());
        ;
        //q_cust 有空查詢的可能
        Customer customer = new Customer();
        customer.setId(0);
        customer.setCustNm("請選擇");

        model.addAttribute("books", bookPage.getContents());
        model.addAttribute("customers", customers);
        model.addAttribute("indexPage", bookPage.getCurrentPage());
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("count", bookPage.getCount());
        model.addAttribute("pageSizeOption", pageSizeOption);
        customers.add(0, customer);
        model.addAttribute("q_customers", customers);
        model.addAttribute("bookReq", bookReq);
        return "book/list";
    }

    //查詢分頁會員列表及修改pageSize
    @GetMapping("/booksChangePage")
    public String changePage(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                             @RequestParam(required = false) String q_id, @RequestParam(required = false) String q_id2,
                             @RequestParam(required = false) Integer q_amt, @RequestParam(required = false) Integer q_amt2,
                             @RequestParam(required = false) String q_invYM, @RequestParam(required = false) String q_invYM2,
                             @RequestParam(required = false) String q_paidDat, @RequestParam(required = false) String q_paidDat2,
                             @RequestParam(required = false) String q_incomeOrExpend, @RequestParam(required = false) String q_invNo,
                             @RequestParam(required = false) Integer q_customerId, @RequestParam(required = false) Integer q_projectId,
                             @RequestParam(required = false) Integer q_invoice, @RequestParam(required = false) Integer q_paid,
                             @RequestParam(required = false) String q_description, Model model) {
        //BookReq bookReq = new BookReq(q_id, q_id2, q_amt, q_amt2, q_invYM, q_invYM2, q_paidDat, q_paidDat2, q_incomeOrExpend, q_invNo, q_customerId, q_projectId, q_invoice, q_paid, q_description);
        LOGGER.info("changePage.query_bookReq= " + bookReq.toStringForQuery());
        LOGGER.info("changePage.page= " + page);
        LOGGER.info("changePage.pageSize= " + pageSize);
        BookPage bookPage = bookService.queryAll(page, pageSize, bookReq);
        List<ProjectDto> projectDtos = null; //for查詢用的ProjectDtoList
        //傳回query 參數
        if (bookReq.getQ_customerId() != null && bookReq.getQ_customerId() != 0) {
            projectDtos = this.findProjectByCustomerId(bookReq.getQ_customerId());
        }
        List<Customer> customers = customerDao.findAll().stream().filter(it -> it.getDeleted() == null || it.getDeleted() != 1).collect(Collectors.toList());

        //q_cust 有空查詢的可能
        Customer customer = new Customer();
        customer.setId(0);
        customer.setCustNm("請選擇");

        model.addAttribute("books", bookPage.getContents());
        model.addAttribute("customers", customers);
        model.addAttribute("indexPage", bookPage.getCurrentPage());
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("count", bookPage.getCount());
        model.addAttribute("pageSizeOption", pageSizeOption);
        customers.add(0, customer);
        model.addAttribute("q_customers", customers);
        model.addAttribute("projectDtos", projectDtos);
        model.addAttribute("bookReq", bookReq);
        return "book/list";
    }

    //查詢form
    @GetMapping("/queryByCondition")
    public String queryByCondition(
            @RequestParam(required = false, defaultValue = "5") Integer hiddenPageSize,
            @RequestParam(required = false) String q_id, @RequestParam(required = false) String q_id2,
            @RequestParam(required = false) Integer q_amt, @RequestParam(required = false) Integer q_amt2,
            @RequestParam(required = false) String q_invYM, @RequestParam(required = false) String q_invYM2,
            @RequestParam(required = false) String q_paidDat, @RequestParam(required = false) String q_paidDat2,
            @RequestParam(required = false) String q_incomeOrExpend, @RequestParam(required = false) String q_invNo,
            @RequestParam(required = false) Integer q_customerId, @RequestParam(required = false) Integer q_projectId,
            @RequestParam(required = false) Integer q_invoice, @RequestParam(required = false) Integer q_paid,
            @RequestParam(required = false) String q_description, Model model) {
        bookReq = new BookReq(q_id, q_id2, q_amt, q_amt2, q_invYM, q_invYM2, q_paidDat, q_paidDat2, q_incomeOrExpend, q_invNo, q_customerId, q_projectId, q_invoice, q_paid, q_description);
        LOGGER.info("query.bookReq= " + bookReq.toStringForQuery());
        LOGGER.info("query.pageSize= " + hiddenPageSize);
        List<ProjectDto> projectDtos = null; //for查詢用的ProjectDtoList

        BookPage bookPage = bookService.queryAll(1, hiddenPageSize, bookReq);
        //傳回query 參數
        if (bookReq.getQ_customerId() != null && bookReq.getQ_customerId() != 0) {
            projectDtos = this.findProjectByCustomerId(bookReq.getQ_customerId());
        }
        List<Customer> customers = customerDao.findAll().stream().filter(it -> it.getDeleted() == null || it.getDeleted() != 1).collect(Collectors.toList());
        //q_cust 有空查詢的可能
        Customer customer = new Customer();
        customer.setId(0);
        customer.setCustNm("請選擇");

        model.addAttribute("books", bookPage.getContents());
        model.addAttribute("customers", customers);
        model.addAttribute("indexPage", bookPage.getCurrentPage());
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("pageSize", hiddenPageSize);
        model.addAttribute("count", bookPage.getCount());
        model.addAttribute("pageSizeOption", pageSizeOption);
        customers.add(0, customer);
        model.addAttribute("q_customers", customers);
        model.addAttribute("projectDtos", projectDtos);
        model.addAttribute("bookReq", bookReq);
        return "book/list";
    }

    public List<ProjectDto> findProjectByCustomerId(Integer customerId) {
        List<Project> projects = projectDao.findByCustomer_Id(customerId);
        List<ProjectDto> projectDtos = projectDao.findByCustomer_Id(customerId).stream()
                .map(this::getProjectDto)
                .collect(Collectors.toList());
        return projectDtos;
//        JSONArray jsArr = JSONArray.fromObject(projects);
//        return jsArr;
    }

    @GetMapping(value = "/books/bookkeepingPDF", produces = "application/octet-stream")
    public void printPDF(@RequestParam(required = false) String q_id, @RequestParam(required = false) String q_id2,
                         @RequestParam(required = false) Integer q_amt, @RequestParam(required = false) Integer q_amt2,
                         @RequestParam(required = false) String q_invYM, @RequestParam(required = false) String q_invYM2,
                         @RequestParam(required = false) String q_paidDat, @RequestParam(required = false) String q_paidDat2,
                         @RequestParam(required = false) String q_incomeOrExpend, @RequestParam(required = false) String q_invNo,
                         @RequestParam(required = false) Integer q_customerId, @RequestParam(required = false) Integer q_projectId,
                         @RequestParam(required = false) Integer q_invoice, @RequestParam(required = false) Integer q_paid,
                         @RequestParam(required = false) String q_description, HttpServletResponse response) throws Exception {
        bookReq = new BookReq(q_id, q_id2, q_amt, q_amt2, q_invYM, q_invYM2, q_paidDat, q_paidDat2, q_incomeOrExpend, q_invNo, q_customerId, q_projectId, q_invoice, q_paid, q_description);
        System.out.println(bookReq.toStringForQuery());
        generatePdf("bookPdf", bookService.queryPdf(bookReq), response);
    }

    private ProjectDto getProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setProjectName(project.getProjectName());
        projectDto.setCustomerId(project.getCustomer().getId());
        return projectDto;
    }

    public void generatePdf(String template, List DtoList, HttpServletResponse response) throws Exception {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariable("Dtos", DtoList);
        String renderedHtmlContent = templateEngine.process("pdf/" + template, context);
        //System.out.println(renderedHtmlContent);
        String xHtml = PdfUtils.convertToXhtml(renderedHtmlContent);
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("pdf/simsun.ttc", IDENTITY_H, EMBEDDED);
        String baseUrl = FileSystems
                .getDefault()
                .getPath("src")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();
        OutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
        renderer.createPDF(outputStream);
        outputStream.close();
        response.setHeader("Content-Disposition", OUTPUT_FILE);
        response.setContentType("application/pdf");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        IOUtils.copy(new FileInputStream(OUTPUT_FILE), response.getOutputStream());
        //response.flushBuffer();
        //IOUtils.copy(new FileInputStream(OUTPUT_FILE), response.getOutputStream());

    }
}
