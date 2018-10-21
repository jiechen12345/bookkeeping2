package com.oppo.business;

import com.oppo.dto.BookDto;
import com.oppo.dto.BookPage;
import com.oppo.request.BookReq;

import java.util.List;

/**
 * Created by JieChen on 2018/10/3.
 */
public interface BookService {
    List<BookDto> findAll();

    BookDto queryOne(String id);

    BookPage getAllForm(Integer page, Integer pageSize);

    BookPage queryAll(Integer page, Integer pageSize, BookReq bookReq);

    void create(BookReq bookReq);

    void update(BookReq bookReq);

    void delete(String id);
}
