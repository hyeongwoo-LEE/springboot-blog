package com.cos.blog.controller.api;


import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDTO<Integer> save(@RequestBody User user){
        System.out.println("UserApiController: save 호출됨" );

        //실제로 DB에 insert 를 하고 아래에서 return 이 되면 돼요.
        int result = userService.회원가입(user);

        return new ResponseDTO<Integer>(HttpStatus.OK.value(),result); //자바오브젝트를 JSON으로 반환(Jackson)
    }

    @PutMapping("/user")
    public ResponseDTO<Integer> update(@RequestBody User user){

       userService.회원수정(user);
        //여기서는 트랜재션이 종료되어 DB에 값은 변경되었지만
        //세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임

        //세션등록
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return new ResponseDTO<>(HttpStatus.OK.value(), 1);
    }
}
