package com.oppo.dao;


import com.oppo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JieChen on 2018/10/3.
 */
@Repository
public interface ProjectDao extends JpaRepository<Project,Integer> {
    List<Project> findByCustomer_Id(Integer customerId);
    List<Integer> findIdByCustomer_Id(Integer customerId);
}
