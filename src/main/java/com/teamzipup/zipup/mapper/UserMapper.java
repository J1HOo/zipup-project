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

    //판매자 저장
    void insertSeller(User user);

    // 로그인
    User findByEmail(String email);
}