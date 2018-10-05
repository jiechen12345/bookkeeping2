package com.oppo.business.impl;

import com.oppo.Entity.Book;
import com.oppo.Entity.Book;
import com.oppo.business.BookService;
import com.oppo.dao.BookDao;
import com.oppo.dao.ProjectDao;
import com.oppo.dto.BookDto;
import com.oppo.dto.BookPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by JieChen on 2018/10/3.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<BookDto> findAll() {
        List<BookDto> bookDtoList = bookDao.findAll(PageRequest.of(0, 5)).stream()
                .map(this::getBookDto)
                .collect(Collectors.toList());
        return bookDtoList;
    }

    @Override
    public BookPage getAllForm(Integer page, Integer PageSize) {
        Page<Book> p = bookDao.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("id")));
            return cb.and();
        }, PageRequest.of(page - 1, PageSize));

        BookPage result = new BookPage();
        result.setContents(p.getContent()
                .stream()
                .map(it -> new BookDto(
                                it.getId(),
                                it.getIncomeOrExpend(),
                                it.getInvoice(),
                                it.getInvYM(),
                                it.getInvNo(),
                                it.getPaid(),
                                it.getPaidDat(),
                                it.getAmt(),
                                it.getProject().getCustomer().getId(),
                                it.getProject().getCustomer().getCustNm(),
                                it.getProject().getId(),
                                it.getProject().getProjectName(),
                                it.getDescription(),
                                it.getRemarks()
                        )
                )
                .collect(toList()));
        result.setCurrentPage(page);
        result.setTotalPages(p.getTotalPages()>0?p.getTotalPages():1);
        result.setCount(p.getTotalElements());
        return result;
    }

    private BookDto getBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setIncomeOrExpend(book.getIncomeOrExpend());
        bookDto.setInvoice(book.getInvoice());
        bookDto.setInvYM(book.getInvYM());
        bookDto.setInvNo(book.getInvNo());
        bookDto.setPaid(book.getPaid());
        bookDto.setPaidDat(book.getPaidDat());
        bookDto.setAmt(book.getAmt());
        bookDto.setDescription(book.getDescription());
        bookDto.setRemarks(book.getRemarks());

        if (book.getProject() != null) {
            bookDto.setProjectId(book.getProject().getId());
            bookDto.setProjectName(book.getProject().getProjectName());
        } else {
            bookDto.setProjectName("");
        }

        return bookDto;
    }
}
