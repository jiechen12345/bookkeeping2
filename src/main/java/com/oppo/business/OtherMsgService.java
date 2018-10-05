package com.oppo.business;


import com.oppo.dto.OtherMsgDto;
import com.oppo.request.OtherMsgReq;

import java.util.List;

/**
 * Created by jiechen on 2018/8/2.
 */
public interface OtherMsgService {
    List<OtherMsgDto> findAll();
    void create(OtherMsgReq otherMsgReq);
    OtherMsgDto findOne(Integer id);
    void update(OtherMsgReq otherMsgReq);
    void delete(Integer id);
}
