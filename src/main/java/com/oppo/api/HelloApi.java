package com.oppo.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by JieChen on 2018/7/20.
 */
@Controller
@RequestMapping("/test")
public class HelloApi {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        System.out.println(55555);
        return "OK";
    }

    @RequestMapping("/success")
    public String success(Map <String,Object> map){
        map.put("success","<h1>成功!</h1>");
        map.put("users", Arrays.asList("杰歌","阿杰","雞雞"));
        map.put("id","id01");

        return "success";
    }
}
