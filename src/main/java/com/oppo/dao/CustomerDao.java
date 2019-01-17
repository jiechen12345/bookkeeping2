package com.oppo.dao;

import com.oppo.Entity.Customer;
import com.oppo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JieChen on 2018/10/5.
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    Integer countCustomerByCustNmAndDeleted(String custNm, Integer delete);

    Integer countByDeletedNotAndCustNm(Integer delete, String custNm);

    Integer countByDeleted(Integer delete);

    Integer countByDeletedNot(Integer delete);

    List<Customer> getAllByDeletedIsNot(Boolean delete);
}

