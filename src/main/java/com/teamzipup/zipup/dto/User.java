package com.teamzipup.zipup.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor    // 필수 생성자
@NoArgsConstructor    // 기본 생성자
@Getter              // getter 줄임말
@Setter              // setter 줄임말
@ToString           // tostring 줄임말

public class User {

    @Id                                                                    // primary key 표기로 id는 맨 위에 작성
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // auto_increment 회원 가입 시 번호 자동적으로 증가

    private long id;                         // '숫자(주소) 아이디'
    private String role;	          // '등급부여(이용자=user,판매자=seller)'
    private String email;                 // '회원 이메일 (고유값)
    private String password;            // '비밀번호'
    private String address;              // '주소'
    private String userName;           // '사용자 이름'
    private String phoneNumber;     // '전화번호'
    private String companyName;    // '상호명 (판매자 전용)'
    private String businessNumber;  // '사업자 등록 번호 (판매자 전용)'11111
}
