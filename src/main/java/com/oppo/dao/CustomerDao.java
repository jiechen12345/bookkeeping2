package com.oppo.dao;

import com.oppo.Entity.Customer;
import com.oppo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by JieChen on 2018/10/5.
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer>{

}

