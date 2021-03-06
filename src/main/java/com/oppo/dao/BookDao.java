package com.oppo.dao;

import com.oppo.Entity.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JieChen on 2018/10/2.
 */
@Repository
public interface BookDao extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {

    Book findFirstByOrderByIdAsc();
    Book findFirstByOrderByIdDesc();
    Book findBookByIdEquals(String id);
}
