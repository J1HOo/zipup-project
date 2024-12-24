package com.teamzipup.zipup.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id; // 사용자 ID
    private String role; // user or seller
    private String email; // 이메일
    private String password; // 비밀번호
    private String address; // 주소
    private String phoneNumber; // 전화번호
    private String userName; // 사용자 이름 (user)
    private String companyName; // 회사명 (seller)
    private String businessNumber; // 사업자 등록 번호 (seller)

}
