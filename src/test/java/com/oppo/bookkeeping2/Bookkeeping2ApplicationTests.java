package com.oppo.bookkeeping2;

import com.oppo.Entity.Member;
import com.oppo.dto.MemberDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;
import static com.itextpdf.text.pdf.BaseFont.NOT_EMBEDDED;
import static org.thymeleaf.templatemode.TemplateMode.HTML;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Bookkeeping2ApplicationTests {
    private static final String OUTPUT_FILE = "test.pdf";
    private static final String UTF_8 = "UTF-8";

    @Test
    public void generatePdf() throws Exception {

        // We set-up a Thymeleaf rendering engine. All Thymeleaf templates
        // are HTML-based files located under "src/test/resources". Beside
        // of the main HTML file, we also have partials like a footer or
        // a header. We can re-use those partials in different documents.
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);
        templateResolver.setCharacterEncoding(UTF_8);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // The data in our Thymeleaf templates is not hard-coded. Instead,
        // we use placeholders in our templates. We fill these placeholders
        // with actual data by passing in an object. In this example, we will
        // write a letter to "John Doe".
        //
        // Note that we could also read this data from a JSON file, a database
        // a web service or whatever.
        List<MemberDto> data = exampleDataForJohnDoe();

        Context context = new Context();
        context.setVariable("data", data);

        // Flying Saucer needs XHTML - not just normal HTML. To make our life
        // easy, we use JTidy to convert the rendered Thymeleaf template to
        // XHTML. Note that this might no work for very complicated HTML. But
        // it's good enough for a simple letter.
        String renderedHtmlContent = templateEngine.process("template", context);

        System.out.println(renderedHtmlContent);
        String xHtml = convertToXhtml(renderedHtmlContent);

        ITextRenderer renderer = new ITextRenderer();
        //renderer.getFontResolver().addFont("Code39.ttf", IDENTITY_H, EMBEDDED);
        renderer.getFontResolver().addFont("simsun.ttc", IDENTITY_H, EMBEDDED);
        //renderer.getFontResolver().addFont("simsun.ttc", IDENTITY_H, EMBEDDED);

        // FlyingSaucer has a working directory. If you run this test, the working directory
        // will be the root folder of your project. However, all files (HTML, CSS, etc.) are
        // located under "/src/test/resources". So we want to use this folder as the working
        // directory.
        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "test", "resources")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        // And finally, we create the PDF:
        OutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
        renderer.createPDF(outputStream);
        outputStream.close();
    }

    private List<MemberDto> exampleDataForJohnDoe() {
        List<MemberDto> members = new ArrayList<MemberDto>();
        MemberDto memberDto = new MemberDto(1, "一二三一二三", "pass", "name", "dep");
        MemberDto memberDto2 = new MemberDto(2, "acc2", "pass2", "name2", "dep2");
        members.add(memberDto);
        members.add(memberDto2);

        return members;
    }



    private String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding(UTF_8);
        tidy.setOutputEncoding(UTF_8);
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString(UTF_8);
    }

}
