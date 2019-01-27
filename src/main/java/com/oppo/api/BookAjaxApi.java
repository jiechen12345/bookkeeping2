package com.oppo.api;

import com.oppo.Entity.Project;
import com.oppo.business.BookService;
import com.oppo.common.PdfUtils;
import com.oppo.dao.ProjectDao;
import com.oppo.dto.BookDto;
import com.oppo.dto.MemberDto;
import com.oppo.dto.ProjectDto;
import com.oppo.dto.chart.ChartDto;
import com.oppo.request.BookReq;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.FileSystems;
import java.util.*;
import java.util.stream.Collectors;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;
import static org.thymeleaf.templatemode.TemplateMode.HTML;

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
    private BookService bookService;
    //------------PDF
    private static final String OUTPUT_FILE = "test.pdf";
    private static final String UTF_8 = "UTF-8";


    @RequestMapping(method = RequestMethod.POST)
    public void creat(@RequestBody BookReq bookReq) {
        System.out.println(bookReq.toString());
        bookService.create(bookReq);

    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody BookReq bookReq) {
        System.out.println(bookReq.toString());
        bookService.update(bookReq);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestBody String bookId) {
        System.out.println("要刪除的= " + bookId);
        bookService.delete(bookId);
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

    @RequestMapping(value = "/queryOne", method = RequestMethod.POST)
    public BookDto queryOne(@RequestBody String bookId) {
        BookDto bookDto = bookService.queryOne(bookId);
        return bookDto;
    }

    @RequestMapping(value = "/lineChart", method = RequestMethod.POST)
    public ChartDto lineChart() {
        ChartDto chartDto = bookService.queryAmtByYear(new Date(), 12);
        return chartDto;
    }

    private ProjectDto getProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setProjectName(project.getProjectName());
        projectDto.setCustomerId(project.getCustomer().getId());
        projectDto.setDeleted(project.getDeleted());
        return projectDto;
    }
    //------------
//    @ResponseBody
//    @RequestMapping(value = "/download",produces="application/octet-stream")
//    public byte[] downloadFile(HttpServletRequest request, HttpServletResponse response, String contentType2)
//            throws IOException {
//        String fileName = new String(OUTPUT_FILE.getBytes(), "ISO8859-1");
//        String cnt = "inline; filename=\"" + fileName + "\"";
//        response.setHeader("Content-Disposition", cnt);
//        response.setContentType("application/pdf");
//        IOUtils.copy(new FileInputStream(fileDto.getFile()), response.getOutputStream());
//    }

}
