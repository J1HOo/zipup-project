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

    @Override
    public String findEmail(String userName, String password) {
        User user = userMapper.findByUserNameAndPassword(userName, password);
        if (user == null) {
            throw new RuntimeException("회원 정보를 찾을 수 없습니다.");
        }
        return user.getEmail();
    }

    @Override
    public String findPassword(String email, String phoneNumber) {
        User user = userMapper.findByEmailAndPhoneNumber(email, phoneNumber);
        if (user == null) {
            throw new RuntimeException("회원 정보를 찾을 수 없습니다.");
        }
        return user.getPassword();
    }

    public User getUserById(long id) {
        return userMapper.findById(id);
    }

    // 판매자 정보 가져오기
    @Override
    public User getUserBySellerId(Long sellerId) {
        return userMapper.getUserBySellerId(sellerId);
    }
}

