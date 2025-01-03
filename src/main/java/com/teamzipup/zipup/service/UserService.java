package com.teamzipup.zipup.service;

import com.teamzipup.zipup.dto.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface UserService {

    // 모든 유저 보기 기능
    List<Map<String, Objects>> getAllUsers();

    // 일반 이용자 저장하는 기능
    void insertUser(User user);

    // 아이디(이메일) 중복 확인 메서드
    boolean isEmailTaken(String email);

    // 판매자 저장
    void insertSeller(User user);

    // 로그인
    User findByEmail(String email);

    // 이메일 찾기
    String findEmail(String username, String password);

    // 비밀번호 찾기
    String findPassword(String email, String phoneNumber);

    // 아이디로 조회
    User getUserById(long id);
    // 판매자 정보 가져오기
    User getUserBySellerId(Long sellerId);
}
