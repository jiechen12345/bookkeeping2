package com.oppo.business;

import com.oppo.dto.MemberDto;
import com.oppo.dto.MemberPage;
import com.oppo.request.MemberReq;

import java.util.List;

/**
 * Created by jiechen on 2018/7/26.
 */
public interface MemberService {
    List<MemberDto> findAll();

    MemberDto findOne(Integer id);

    MemberPage getAllForm(Integer page,Integer pageSize);

    void create(MemberReq memberReq);

    void update(MemberReq memberReq);

    void delete(Integer id);
}
