package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {


    @GetMapping({"","/"}) // 컨트롤러에서는 세션을 어떻게 찾는지?
    public String index(){
        //WEB-INF/views/index.jsp
        return "index";
    }

    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "/board/saveForm";
    }

}
