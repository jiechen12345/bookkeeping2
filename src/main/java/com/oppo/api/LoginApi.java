package com.oppo.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by jiechen on 2018/7/23.
 */
@Controller
@RequestMapping("/api")
public class LoginApi {

    @PostMapping(value = "/login")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password
            , Map<String, Object> map, HttpSession session,HttpServletRequest request) throws IOException {
        if (!StringUtils.isEmpty(account) && "123".equals(password)) {
            //String ip=getIpAddress(request);
            session.setAttribute("loginUser", account);
            return "redirect:/main.html";
        } else {
            map.put("msg", "帳號或密碼錯誤");
            return "index";
        }
    }


}
