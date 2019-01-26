package com.oppo.business;

import com.oppo.dto.BookDto;
import com.oppo.dto.BookPage;
import com.oppo.dto.pdf.BookPdfDto;
import com.oppo.request.BookReq;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by JieChen on 2018/10/3.
 */
public interface BookService {
    List<BookDto> findAll();

    BookDto queryOne(String id);

    BookPage getAllForm(Integer page, Integer pageSize);

    BookPage getAllForm(Integer page, Integer pageSize, String nowYM);

    BookPage queryAll(Integer page, Integer pageSize, BookReq bookReq);

    List<BookPdfDto> queryPdf(BookReq bookReq);

    Map<String, List<Double>> queryAmtByYear(Date q_date, Integer month);

    void create(BookReq bookReq);

    void update(BookReq bookReq);

    void delete(String id);
}
