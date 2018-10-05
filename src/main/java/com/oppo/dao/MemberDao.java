package com.oppo.dao;

import com.oppo.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by JieChen on 2018/7/24.
 */
@Repository
public interface MemberDao extends JpaRepository<Member, Integer>, JpaSpecificationExecutor<Member> {
    Member findByAccount(String account);
}
