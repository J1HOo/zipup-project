package com.teamzipup.zipup.mapper;

import com.teamzipup.zipup.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // 모든 유저 목록 조회
    List<User> getAllUsers();

    //일반 이용자 저장하기
    void insertUser(User user);

    // 아이디(이메일) 중복 여부 확인 메서드
    int existsByEmail(String email);

    //판매자 저장
    void insertSeller(User user);

    // 로그인
    User findByEmail(String email);

    // 아이디 찾기
    User findByUserNameAndPassword(String userName, String password);

    // 비밀번호 찾기
    User findByEmailAndPhoneNumber(String email, String phoneNumber);

    // 아이디로 조회
    User findById(long id);

}