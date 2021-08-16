package com.cos.blog.test;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController //html파일이 아니라 data를 리턴해주는 controller = RestController
public class DummyControllerTest {

    private final UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable("id") int id){

        try{
            userRepository.deleteById(id);

        }catch(EmptyResultDataAccessException e){
            return "삭제에 실패하였습니다. 해당 ID는 DB에 없습니다.";
        }

        return "삭제되었습니다. id: " + id;
}


    //save 함수는 id를 전달하지 않으면 insert
    //save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
    //save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert
    //email, password
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable("id") int id, @RequestBody User requestUser){
        //json 데이터를 요청 -> Java Object(MessageConverter의 Jackson 라이브러리가 변환해서 받아줘요.

        System.out.println("id"+ id);
        System.out.println("requestUser.getPassword() = " + requestUser.getPassword());
        System.out.println("requestUser.getEmail() = " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("수정에 실패하였습니다."));

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        //userRepository.save(user);

        //더치체킹

        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }


    @GetMapping("/dummy/user")
    public Page<User> pageList(
            @PageableDefault(page= 1, size=2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){

        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();

        return pagingUser;

    }


    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable("id") int id){

        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 유저는 없습니다. id: "+id)); // aop를 이용해 에러 처리

        //요청 : 웹브라우저
        //user 객체 : 자바 오브젝트
        //변환 (웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
        //스프링부트 = MessageConverter 라는 애가 응답시에 자동 작동
        //만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        //user 오브젝트를 json으로 변환해서 브라우저에게 전달

        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user){

        System.out.println("username: " +user.getUsername());
        System.out.println("password " +user.getPassword());
        System.out.println("email: " +user.getEmail());
        System.out.println(user.toString());


        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }
}
