package com.oppo.dao;


import com.oppo.Entity.OtherMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by jiechen on 2018/8/2.
 */
@Repository
public interface OtherMsgDao extends JpaRepository<OtherMsg, Integer>, JpaSpecificationExecutor<OtherMsg> {
}
