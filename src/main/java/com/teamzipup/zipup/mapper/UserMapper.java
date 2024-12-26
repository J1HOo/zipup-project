package com.teamzipup.zipup.mapper;

import com.teamzipup.zipup.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // sql 에 작성한 id를 가져와서 자바에서 사용하겠다는 설정 표기

public interface UserMapper {
    // 모든 유저 목록 조회
    List<User> getAllUsers();
    //유저 저장하기
    void insertUser(User user);
}
