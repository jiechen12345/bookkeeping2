package com.oppo.api;

import com.oppo.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JieChen on 2018/9/17.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value ="/test",produces = "application/json")
public class Test {
    @RequestMapping(value = "/search" ,method = RequestMethod.POST)
    public MemberDto find(@RequestBody Integer id) {
        System.out.println(1231233122);
        MemberDto memberDto=new MemberDto();
        memberDto.setName("Jony");
        memberDto.setAccount("Z01");
        return memberDto;
    }
}
