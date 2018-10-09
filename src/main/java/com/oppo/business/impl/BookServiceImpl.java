package com.oppo.business.impl;

import com.oppo.Entity.*;
import com.oppo.Entity.Book;
import com.oppo.business.BookService;
import com.oppo.common.Common;
import com.oppo.dao.BookDao;
import com.oppo.dao.CustomerDao;
import com.oppo.dao.MemberDao;
import com.oppo.dao.ProjectDao;
import com.oppo.dto.BookDto;
import com.oppo.dto.BookPage;
import com.oppo.request.BookReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private MemberDao memberDao;
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
            query.orderBy(cb.desc(root.get("id")));
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
                                it.getCreateDat(),
                                it.getUpdateDat(),
                        (it.getCreateMember()!=null)?it.getCreateMember().getId():0,
                        (it.getCreateMember()!=null)?it.getCreateMember().getName():"",
                        (it.getUpdateMember()!=null)?it.getUpdateMember().getId():0,
                        (it.getUpdateMember()!=null)?it.getUpdateMember().getName():"",
                                it.getDescription(),
                                it.getRemarks()
                        )
                )
                .collect(toList()));
        result.setCurrentPage(page);
        result.setTotalPages(p.getTotalPages() > 0 ? p.getTotalPages() : 1);
        result.setCount(p.getTotalElements());
        return result;
    }

    @Override
    public void create(BookReq bookReq) {
        bookReq.setId(getSerialNumber());
        Book book = getSetupBook(bookReq);
        bookDao.save(book);
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

    private Book getSetupBook(BookReq bookReq) {
        Book book = new Book();
        book.setId(bookReq.getId());
        book.setIncomeOrExpend(bookReq.getIncomeOrExpend());
        book.setInvoice((bookReq.getInvoice() == 1) ? true : false);
        book.setInvYM(Common.get(bookReq.getInvYM()));
        book.setInvNo(Common.get(bookReq.getInvNo()));
        book.setPaid((bookReq.getPaid() == 1) ? true : false);
        book.setPaidDat(bookReq.getPaidDat());
        book.setAmt(Common.get(bookReq.getAmt()));
        book.setDescription(Common.get(bookReq.getDescription()));
        book.setRemarks(Common.get(bookReq.getRemarks()));
        book.setCreateDat(bookReq.getCreateDat());

        if (bookReq.getCreatememberId() != null) {
            Member member = memberDao.findById(bookReq.getCreatememberId()).get();
            book.setCreateMember(member);
        }
        if (bookReq.getProjectId() != null) {
            Project project = projectDao.findById(Integer.parseInt(bookReq.getProjectId())).get();
            book.setProject(project);
        }
        return book;
    }

    //流水號
    private String getSerialNumber() {
        String num = "";
        String now = LocalDate.now().toString().replace("-", "");
        Book book = bookDao.findFirstByOrderByIdDesc();
        if (book != null && book.getId().length() > 8 && now.equals(book.getId().substring(0, 8))) { //如果當天已有
            Long maxId = Long.parseLong(book.getId());
            maxId = maxId + 1;
            num = maxId.toString();
        } else {
            num = now + "0001";
        }
        return num;
    }
}
