package com.cos.blog.test;

import lombok.*;

@Data
@NoArgsConstructor
public class Member {

    //private 함수에 다이렉트로 접근하는 것을 막음 -> public 메서드를 작성해 접근

    private int id;

    private String username;

    private String password;

    private String email;


    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
