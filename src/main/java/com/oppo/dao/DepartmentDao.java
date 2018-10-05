package com.oppo.dao;

import com.oppo.Entity.Departemt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by JieChen on 2018/7/24.
 */
@Repository
public interface DepartmentDao extends JpaRepository<Departemt,Integer> {
}
