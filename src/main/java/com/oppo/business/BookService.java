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

//    BookDto findOne(Integer id);

    BookPage getAllForm(Integer page, Integer pageSize);
//    void create(BookReq memberReq);
//
//    void update(BookReq memberReq);
//
//    void delete(Integer id);
}
