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
import com.oppo.dto.chart.ChartDto;
import com.oppo.dto.pdf.BookPdfDto;
import com.oppo.request.BookReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.joda.time.DateTime;

import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
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
    private static Integer sum = 0;
    private static Integer inSum = 0;
    private static Integer exSum = 0;
    private static final String inTotal = "inTotal";
    private static final String exTotal = "exTotal";
    private static final String total = "total";

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
    public BookPage getAllForm(Integer page, Integer pageSize, String nowYM) {
        Page<Book> p = bookDao.findAll((root, query, cb) -> {
            query.orderBy(cb.desc(root.get("id")));

            List<Predicate> predicates = new LinkedList<>();

            Optional.ofNullable(nowYM).filter(it -> !it.isEmpty()).ifPresent(nowYM2 -> {
                predicates.add(cb.like(root.get("id"), nowYM2 + "%"));
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

            Optional.ofNullable(bookReq.getQ_customerId()).filter(it -> it != 0).ifPresent(q_customerId -> {
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
    public List<BookPdfDto> queryPdf(BookReq bookReq) {

        List<Book> books = bookDao.findAll((root, query, cb) -> {
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

            Optional.ofNullable(bookReq.getQ_customerId()).filter(it -> it != 0).ifPresent(q_customerId -> {
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
        });

        List<BookPdfDto> BookPdfDtos = books.stream().map(this::getBookPdfDto).collect(Collectors.toList());
        BookPdfDto bookPdfDto = new BookPdfDto(" ", " ", " ", " ", " ", " ", "小計", Common.formatAmt(inSum), Common.formatAmt(exSum), Common.formatAmt(sum), " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ");
        BookPdfDtos.add(bookPdfDto);
        return BookPdfDtos;
    }

    @Override
    public ChartDto queryAmtByYear(Date q_date, Integer month) {
        List<Double> incomeList = new ArrayList<Double>();
        List<Double> expendList = new ArrayList<Double>();
        List<Double> profitList = new ArrayList<Double>();
        List<String> monthList = new ArrayList<String>();

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < month; i++) {
            calendar.setTime(q_date);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - i);
            Date q_paidDat = calendar.getTime();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM");
            String mon = dateFormatter.format(q_paidDat);
            System.out.println(mon);
            monthList.add(mon);
            int MinDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
            int MaxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), MinDay, 00, 00, 00);
            Date q_paidStartDat = calendar.getTime();
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), MaxDay, 23, 59, 59);
            Date q_paidEndDat = calendar.getTime();
            System.out.println(q_paidStartDat);
            System.out.println(q_paidEndDat);


            List<Book> books = bookDao.findAll((root, query, cb) -> {
                query.orderBy(cb.desc(root.get("id")));

                List<Predicate> predicates = new LinkedList<>();
                Optional.ofNullable(q_paidStartDat).ifPresent(q_paidDat1 -> {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("paidDat"), q_paidDat1));
                });
                Optional.ofNullable(q_paidEndDat).ifPresent(q_paidDat2 -> {
                    predicates.add(cb.lessThanOrEqualTo(root.get("paidDat"), q_paidDat2));
                });
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            });

            Double inTotalAmt = 0.0;
            Double exTotalAmt = 0.0;
            Double totalAmt = 0.0;
            for (Book book : books) {
                if ("1".equals(book.getIncomeOrExpend())) {
                    inTotalAmt += book.getAmt();
                } else if ("0".equals(book.getIncomeOrExpend())) {
                    exTotalAmt += book.getAmt();
                }
            }
            totalAmt += inTotalAmt - exTotalAmt;
//            incomeList.add(inTotalAmt != 0 ? inTotalAmt / 1000 : 0.0);
//            expendList.add(exTotalAmt != 0 ? exTotalAmt / 1000 : 0.0);
//            profitList.add(totalAmt != 0 ? totalAmt / 1000 : 0.0);
            incomeList.add(inTotalAmt);
            expendList.add(exTotalAmt);
            profitList.add(totalAmt);
        }
        Collections.reverse(monthList);
        Collections.reverse(incomeList);
        Collections.reverse(expendList);
        Collections.reverse(profitList);
        return new ChartDto(incomeList, expendList, profitList, monthList);
        //return null;
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

    private BookPdfDto getBookPdfDto(Book book) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        BookPdfDto bookPdfDto = new BookPdfDto();
        bookPdfDto.setInvoice((book.getInvoice()) ? "Y" : "N");
        bookPdfDto.setInvYM(book.getInvYM().replace("-", ""));
        bookPdfDto.setInvNo(book.getInvNo());
        bookPdfDto.setPaid((book.getPaid()) ? "Y" : "N");
        bookPdfDto.setPaidDat((book.getPaidDat() != null) ? dateFormatter.format(book.getPaidDat()) : "");

        if ("0".equals(book.getIncomeOrExpend())) {
            bookPdfDto.setExAmt(Common.formatAmt(book.getAmt()));
            this.exSum += book.getAmt();
            this.sum -= book.getAmt();
            bookPdfDto.setSum(Common.formatAmt(sum));
        } else if ("1".equals(book.getIncomeOrExpend())) {
            bookPdfDto.setInAmt(Common.formatAmt(book.getAmt()));
            this.inSum += book.getAmt();
            this.sum += book.getAmt();
            bookPdfDto.setSum(Common.formatAmt(sum));
        }

        if (book.getProject() != null) {
            bookPdfDto.setProjectName(book.getProject().getProjectName());
            //bookPdfDto.setCustomerNm(book.getProject().getCustomer().getCustNm());
        } else {
            bookPdfDto.setProjectName("");
        }
        bookPdfDto.setDescription(book.getDescription());
        bookPdfDto.setRemarks(book.getRemarks());

        return bookPdfDto;
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
