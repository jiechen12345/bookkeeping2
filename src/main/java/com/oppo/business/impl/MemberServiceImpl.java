package com.oppo.business.impl;

import com.oppo.Entity.Departemt;
import com.oppo.Entity.Member;
import com.oppo.business.MemberService;
import com.oppo.dao.DepartmentDao;
import com.oppo.dao.MemberDao;
import com.oppo.dto.MemberDto;
import com.oppo.dto.MemberPage;
import com.oppo.request.MemberReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by jiechen on 2018/7/26.
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<MemberDto> findAll() {
        List<MemberDto> memberDtoList = memberDao.findAll( PageRequest.of(0,5)).stream()
                .map(this::getMemberDto)
                .collect(Collectors.toList());
        return memberDtoList;
    }

    @Override
    public MemberPage getAllForm(Integer page, Integer PageSize) {

        Page<Member> p = memberDao.findAll((root, query, cb) -> {
            query.orderBy(cb.asc(root.get("id")));
            return cb.and();
        }, PageRequest.of(page - 1, PageSize));

        MemberPage result = new MemberPage();
        result.setContents(p.getContent()
                .stream()
                .map(it -> new MemberDto(
                                it.getId(),
                                it.getAccount(),
                                it.getPassword(),
                                it.getName(),
                                it.getDepartemt().getDepName()

                        )
                )
                .collect(toList()));
        result.setCurrentPage(page);
        result.setTotalPages(p.getTotalPages());
        result.setCount(p.getTotalElements());
        return result;
    }

    @Override
    public void create(MemberReq memberReq) {
        Member member = new Member();
        member.setAccount(memberReq.getAccount());
        member.setName(memberReq.getName());
        member.setPassword(memberReq.getPassword());
        Departemt departemt = departmentDao.findById(Integer.parseInt(memberReq.getDepId())).get();
        member.setDepartemt(departemt);
        memberDao.save(member);

    }

    @Override
    public MemberDto findOne(Integer id) {
        Member member = memberDao.findById(id).get();
        return getMemberDto(member);
    }


    @Override
    public void update(MemberReq memberReq) {
        Member member = memberDao.findById(Integer.parseInt(memberReq.getId())).get();
        member.setName(memberReq.getName());
        member.setAccount(memberReq.getAccount());
        member.setPassword(memberReq.getPassword());
        Departemt departemt = departmentDao.findById(Integer.parseInt(memberReq.getDepId())).get();
        member.setDepartemt(departemt);
        memberDao.save(member);
    }

    @Override
    public void delete(Integer id) {
        Member member = memberDao.findById(id).get();
        memberDao.delete(member);
    }

    private MemberDto getMemberDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setAccount(member.getAccount());
        memberDto.setPassword(member.getPassword());
        memberDto.setName(member.getName());
        if (member.getDepartemt() != null) {
            memberDto.setDepId(member.getDepartemt().getId());
            memberDto.setDepName(member.getDepartemt().getDepName());
        } else {
            memberDto.setDepName("");
        }
        return memberDto;
    }
}
