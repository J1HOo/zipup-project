package com.teamzipup.zipup.service;

import com.teamzipup.zipup.dto.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface UserService {

    // 모든 유저 보기 기능
    List<Map<String, Objects>> getAllUsers();

    // 유저 저장하는 기능
    void insertUser(User user);

    // 로그인
    User findByEmail(String email);
}
