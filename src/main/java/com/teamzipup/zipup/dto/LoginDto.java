package com.teamzipup.zipup.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String email; // 로그인 이메일
    private String password; // 로그인 비밀번호
}
