package com.oppo.api;

import com.oppo.business.OtherMsgService;
import com.oppo.dto.OtherMsgDto;
import com.oppo.request.OtherMsgReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by jiechen on 2018/8/2.
 */
@Controller
public class OtherMsgApi {
    Logger LOGGER = LoggerFactory.getLogger(OtherMsgApi.class);
    @Autowired
    private OtherMsgService otherMsgService;

    //查詢其他訊息
    @GetMapping("/otherMsgs")
    public String list(Model model) {
        List<OtherMsgDto> otherMsgDtoList = otherMsgService.findAll();
        model.addAttribute("otherMsgs", otherMsgDtoList);
        return "otherMsg/otherMsgList";

    }
    //toAddOtherMsg
    @GetMapping("/otherMsg")
    public String toAddOtherMsg(Model model) {
        //todo:是否還有預設值待後續補入
        return "otherMsg/addOtherMsg";

    }

    //新增其他訊息
    @PostMapping("/otherMsg")
    public String AddOtherMsg(OtherMsgReq otherMsgReq) throws IOException {
        System.out.println(otherMsgReq.toString());
        otherMsgService.create(otherMsgReq);
        return "redirect:/otherMsgs";
    }

    @GetMapping("/otherMsg/{id}")
    public String toModifyOtherMsg(@PathVariable Integer id , Model model) throws IOException {
        OtherMsgDto otherMsgDto = otherMsgService.findOne(id);
        model.addAttribute("msg", otherMsgDto);
        return "otherMsg/addOtherMsg";
    }
    @PutMapping("/otherMsg")
    public String ModifyOtherMsg(OtherMsgReq otherMsgReq ) throws IOException {
        LOGGER.info("***** " + otherMsgReq.toString());
        otherMsgService.update(otherMsgReq);
        return "redirect:/otherMsgs";
    }
    @RequestMapping(value = "/otherMsg/{id}", method = RequestMethod.DELETE)
    public String deleteMember(@PathVariable Integer id) {
        otherMsgService.delete(id);
        return "redirect:/otherMsgs";
    }
}
