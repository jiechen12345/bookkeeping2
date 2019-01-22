package com.oppo.api;

import com.oppo.Entity.Project;
import com.oppo.business.BookService;
import com.oppo.common.PdfUtils;
import com.oppo.dao.ProjectDao;
import com.oppo.dto.BookDto;
import com.oppo.dto.MemberDto;
import com.oppo.dto.ProjectDto;
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
import java.util.ArrayList;
import java.util.List;
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

    @RequestMapping(value = "/print", method = RequestMethod.POST)
    public void printPDF(HttpServletResponse response) throws Exception {
        generatePdf("template", getBookDto(), response);
    }

    private ProjectDto getProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setProjectName(project.getProjectName());
        projectDto.setCustomerId(project.getCustomer().getId());
        return projectDto;
    }

    private List<MemberDto> getBookDto() {
        List<MemberDto> memberDtos = new ArrayList<MemberDto>();
        MemberDto memberDto = new MemberDto(1, "一二三一二三", "pass", "name", "dep");
        MemberDto memberDto2 = new MemberDto(2, "acc2", "pass2", "name2", "dep2");
        memberDtos.add(memberDto);
        memberDtos.add(memberDto2);
        return memberDtos;
    }
//------------PDF-------------------------

    public void generatePdf(String template, List DtoList, HttpServletResponse response) throws Exception {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariable("memberDtos", DtoList);
        String renderedHtmlContent = templateEngine.process("pdf/" + template, context);
        System.out.println(renderedHtmlContent);

//todo:break
        String xHtml = PdfUtils.convertToXhtml(renderedHtmlContent);
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("pdf/simsun.ttc", IDENTITY_H, EMBEDDED);
        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "resources")
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
        response.flushBuffer();
        IOUtils.copy(new FileInputStream(OUTPUT_FILE), response.getOutputStream());
        //ExcelUtil.downloadFile(request, response, fileName, filePath);
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
