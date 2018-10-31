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
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.joda.time.DateTime;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
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
                                (it.getCreateMember() != null) ? it.getCreateMember().getId() : 0,
                                (it.getCreateMember() != null) ? it.getCreateMember().getName() : "",
                                (it.getUpdateMember() != null) ? it.getUpdateMember().getId() : 0,
                                (it.getUpdateMember() != null) ? it.getUpdateMember().getName() : "",
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
    public BookPage queryAll(Integer page, Integer pageSize, BookReq bookReq) {
        Page<Book> p = bookDao.findAll((root, query, cb) -> {
            query.orderBy(cb.desc(root.get("id")));

            List<Predicate> predicates = new LinkedList<>();

            Optional.ofNullable(bookReq.getQ_id()).filter(it -> !it.isEmpty()).ifPresent(q_id -> {
                predicates.add(cb.greaterThanOrEqualTo(root.get("id"), Long.parseLong(q_id)));
            });
            Optional.ofNullable(bookReq.getQ_id2()).filter(it -> !it.isEmpty()).ifPresent(q_id2 -> {
                predicates.add(cb.lessThanOrEqualTo(root.get("id"), Long.parseLong(q_id2)));
            });
            Optional.ofNullable(bookReq.getQ_amt()).ifPresent(q_amt -> {
                predicates.add(cb.ge(root.get("amt"), q_amt));
            });
            Optional.ofNullable(bookReq.getQ_amt2()).ifPresent(q_amt2 -> {
                predicates.add(cb.le(root.get("amt"), q_amt2));
            });
            Optional.ofNullable(bookReq.getQ_invYM()).filter(it -> !it.isEmpty()).ifPresent(q_invYm -> {
                predicates.add(cb.greaterThanOrEqualTo(root.get("invYM"), (q_invYm)));
            });
            Optional.ofNullable(bookReq.getQ_invYM2()).filter(it -> !it.isEmpty()).ifPresent(q_invYm2 -> {
                predicates.add(cb.lessThanOrEqualTo(root.get("invYM"), (q_invYm2)));
            });
            Optional.ofNullable(bookReq.getQ_paidDat()).filter(it -> !it.isEmpty()).ifPresent(q_paidDat -> {
                predicates.add(cb.greaterThanOrEqualTo(root.get("paidDat"), new DateTime(q_paidDat).withTimeAtStartOfDay().toDate()));
            });
            Optional.ofNullable(bookReq.getQ_paidDat2()).filter(it -> !it.isEmpty()).ifPresent(q_paidDat2 -> {
                predicates.add(cb.lessThanOrEqualTo(root.get("paidDat"), new DateTime(q_paidDat2).withTimeAtStartOfDay().plusDays(1).minusMillis(1).toDate()));
            });
            Optional.ofNullable(bookReq.getQ_incomeOrExpend()).filter(it -> !it.isEmpty()).ifPresent(q_incomeOrExpend -> {
                predicates.add(cb.equal(root.get("incomeOrExpend"), q_incomeOrExpend));
            });
            Optional.ofNullable(bookReq.getQ_invNo()).filter(it -> !it.isEmpty()).ifPresent(q_invNo -> {
                predicates.add(cb.like(root.get("invNo"), "%" + q_invNo + "%"));
            });

            Optional.ofNullable(bookReq.getQ_customerId()).filter(it -> it!=0).ifPresent(q_customerId -> {
                predicates.add(cb.equal(root.get("project").get("customer").get("id"), q_customerId));
            });

            Optional.ofNullable(bookReq.getQ_projectId()).ifPresent(q_projectId -> {
                predicates.add(cb.equal(root.get("project").get("id"), q_projectId));
            });

//            if (!"".equals(bookReq.getQ_invNo())){
//                Predicate invNo = cb.like(root.get("invNo"), '%'+bookReq.getQ_invNo()+'%');
//                predicates.add(invNo);
//            }

            Optional.ofNullable(bookReq.getQ_invoice()).ifPresent(q_invoice -> {
                predicates.add(cb.equal(root.get("invoice"), q_invoice));
            });
            Optional.ofNullable(bookReq.getQ_paid()).ifPresent(q_paid -> {
                predicates.add(cb.equal(root.get("paid"), q_paid));
            });
            Optional.ofNullable(bookReq.getQ_description()).ifPresent(q_description -> {
                predicates.add(cb.like(root.get("description"), '%' + q_description + '%'));
            });

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, PageRequest.of(page - 1, pageSize));

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
                                (it.getCreateMember() != null) ? it.getCreateMember().getId() : 0,
                                (it.getCreateMember() != null) ? it.getCreateMember().getName() : "",
                                (it.getUpdateMember() != null) ? it.getUpdateMember().getId() : 0,
                                (it.getUpdateMember() != null) ? it.getUpdateMember().getName() : "",
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
        Book book = new Book();
        book = getSetupBook(book, bookReq);
        bookDao.saveAndFlush(book);
    }

    @Override
    public void update(BookReq bookReq) {
        Book book = bookDao.findById(bookReq.getId()).get();
        book = getSetupBook(book, bookReq);
        //Book book = getSetupBook(bookReq, );
        bookDao.save(book);
    }

    @Override
    public void delete(String id) {
        Book book = bookDao.findById(id).get();
        bookDao.delete(book);
    }

    @Override
    public BookDto queryOne(String bookId) {
        Book book = bookDao.findById(bookId).get();
        BookDto bookDto = getBookDto(book);
        return bookDto;
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
        bookDto.setCreateDat(book.getCreateDat());
        bookDto.setUpdateDat(book.getUpdateDat());
        if (book.getCreateMember() != null) {
            bookDto.setCreateMemberId(book.getCreateMember().getId());
            bookDto.setCreateMemberName(book.getCreateMember().getName());
        }
        if (book.getUpdateMember() != null) {
            bookDto.setUpdateMemberId(book.getUpdateMember().getId());
            bookDto.setUpdateMemberName(book.getUpdateMember().getName());
        }
        if (book.getProject() != null) {
            bookDto.setProjectId(book.getProject().getId());
            bookDto.setProjectName(book.getProject().getProjectName());
            bookDto.setCustomerId(book.getProject().getCustomer().getId());
            bookDto.setCustomerNm(book.getProject().getCustomer().getCustNm());
        } else {
            bookDto.setProjectName("");
        }
        return bookDto;
    }

    private Book getSetupBook(Book book, BookReq bookReq) {
        //Book book = new Book();
        System.out.println(bookReq.toString());
        if ("create".equals(bookReq.getStatus())) {
            book.setId(bookReq.getId());
            book.setCreateDat(bookReq.getCreateDat());
            if (bookReq.getCreateMemberId() != null) {
                Member member = memberDao.findById(bookReq.getCreateMemberId()).get();
                book.setCreateMember(member);
            }
        }
        if ("update".equals(bookReq.getStatus())) {
            if (bookReq.getUpdateMemberId() != null) {
                Member member = memberDao.findById(bookReq.getUpdateMemberId()).get();
                book.setUpdateMember(member);
            }
            book.setUpdateDat(bookReq.getUpdateDat());
        }
        book.setIncomeOrExpend(bookReq.getIncomeOrExpend());
        book.setInvoice((bookReq.getInvoice() == 1) ? true : false);
        book.setInvYM(Common.get(bookReq.getInvYM()));
        book.setInvNo(Common.get(bookReq.getInvNo()));
        book.setPaid((bookReq.getPaid() == 1) ? true : false);
        book.setPaidDat(bookReq.getPaidDat());
        book.setAmt(Common.get(bookReq.getAmt()));
        book.setDescription(Common.get(bookReq.getDescription()));
        book.setRemarks(Common.get(bookReq.getRemarks()));
        if (bookReq.getProjectId() != null) {
            Project project = projectDao.findById((bookReq.getProjectId())).get();
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
