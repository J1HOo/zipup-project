package com.teamzipup.zipup.service;

import com.teamzipup.zipup.mapper.UserMapper;
import com.teamzipup.zipup.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String, Objects>> getAllUsers() {
        return List.of();
    }

    /*이용자 회원가입 */
    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
    /*아이디(이메일) 중복 확인*/
    @Override
    public boolean isEmailTaken(String email) {
        return userMapper.existsByEmail(email)>0;
    }

    @Override
    public void insertSeller(User user) {
        userMapper.insertSeller(user);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }
}
