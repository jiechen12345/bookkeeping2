package com.oppo.business.impl;

import com.oppo.Entity.OtherMsg;
import com.oppo.business.OtherMsgService;
import com.oppo.dao.OtherMsgDao;
import com.oppo.dto.OtherMsgDto;
import com.oppo.request.OtherMsgReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jiechen on 2018/8/2.
 */
@Service
public class OtherMsgServiceImpl implements OtherMsgService {

    @Autowired
    private OtherMsgDao otherMsgDao;

    @Override
    public List<OtherMsgDto> findAll() {
        List<OtherMsgDto> otherMsgDtoList = otherMsgDao.findAll().stream()
                .map(this::getOtherMsgDto)
                .collect(Collectors.toList());
        return otherMsgDtoList;
    }

    @Override
    public void create(OtherMsgReq otherMsgReq) {
        OtherMsg otherMsg = new OtherMsg();
        otherMsg.setTitle(otherMsgReq.getTitle());
        otherMsg.setCtr(0);
        otherMsg.setEditor(otherMsgReq.getEditor());
        otherMsg.setOnShelfDate(otherMsgReq.getOnShelfDate());
        otherMsg.setOffShelfDate(otherMsgReq.getOffShelfDate());
        //Member member = memberDao.findByAccount(otherMsgReq.getLoginUser());
        otherMsg.setCreatedBy(otherMsgReq.getLoginUser());
        otherMsg.setCreatedAt(new Date());
        otherMsg.setUpdatedBy(otherMsgReq.getLoginUser());
        otherMsg.setUpdatedAt(new Date());
        //Departemt departemt = departmentDao.findById(Integer.parseInt(otherMsgReq.getDepId())).get();
        //otherMsg.setDepartemt(departemt);
        otherMsgDao.save(otherMsg);
    }


    @Override
    public OtherMsgDto findOne(Integer id) {
        OtherMsg otherMsg = otherMsgDao.findById(id).get();
        return getOtherMsgDto(otherMsg);
    }

    @Override
    public void update(OtherMsgReq otherMsgReq) {
        OtherMsg otherMsg = otherMsgDao.findById(otherMsgReq.getId()).get();
        otherMsg.setTitle(otherMsgReq.getTitle());
        otherMsg.setEditor(otherMsgReq.getEditor());
        otherMsg.setOnShelfDate(otherMsgReq.getOnShelfDate());
        otherMsg.setOffShelfDate(otherMsgReq.getOffShelfDate());
        otherMsg.setUpdatedBy(otherMsgReq.getLoginUser());
        otherMsg.setUpdatedAt(new Date());
        otherMsgDao.save(otherMsg);
    }
    @Override
    public void delete(Integer id) {
        OtherMsg otherMsg = otherMsgDao.findById(id).get();
        otherMsgDao.delete(otherMsg);
    }


    private OtherMsgDto getOtherMsgDto(OtherMsg otherMsg) {
        OtherMsgDto otherMsgDto = new OtherMsgDto();
        otherMsgDto.setId(otherMsg.getId());
        otherMsgDto.setTitle(otherMsg.getTitle());
        otherMsgDto.setEditor(otherMsg.getEditor());
        otherMsgDto.setCtr(otherMsg.getCtr());
        otherMsgDto.setOnShelfDate(otherMsg.getOnShelfDate());
        otherMsgDto.setOffShelfDate(otherMsg.getOffShelfDate());
        otherMsgDto.setUpdatedBy(otherMsg.getUpdatedBy());
        otherMsgDto.setUpdatedAt(otherMsg.getUpdatedAt());
        otherMsgDto.setCreatedBy(otherMsg.getUpdatedBy());
        otherMsgDto.setCreatedAt(otherMsg.getUpdatedAt());
        return otherMsgDto;
    }
}
