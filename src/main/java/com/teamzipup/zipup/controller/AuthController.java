package com.teamzipup.zipup.controller;


import com.teamzipup.zipup.dto.User;
import com.teamzipup.zipup.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // 이용자 회원가입
    @PostMapping("/signup/user")
    public String userSignup(@ModelAttribute("user") User user, Model model) {
        if (userService.isEmailTaken(user.getEmail())) {
            model.addAttribute("error", "중복된 이메일입니다. 다른 이메일을 입력하세요.");
            return "userSignup"; // 회원가입 페이지로 다시 이동
        }
        user.setRole("user"); // 이용자 역할 설정
        userService.insertUser(user);
        model.addAttribute("msg", "회원가입 성공 (이용자)");
        return "index";
    }
    // 이메일 중복체크
    @GetMapping("/check-email")
    @ResponseBody
    public Map<String, Boolean> checkEmail(@RequestParam String email) {
        boolean isTaken = userService.isEmailTaken(email);
        return Map.of("isTaken", isTaken);
    }


    // 판매자 회원가입
    @PostMapping("/signup/seller")
    public String sellerSignup(@ModelAttribute("user") User user, Model model) {
        user.setRole("seller"); // 판매자 역할 설정
        userService.insertSeller(user);
        model.addAttribute("msg", "회원가입 성공 (판매자)");
        return "index";
    }


    // 로그인 파트
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        User logginUser = (User) session.getAttribute("loginUser");

        if (logginUser != null) {
            model.addAttribute("user", logginUser);
        }

        return "index";
    }


    @PostMapping("/login")
    public String login(
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        HttpSession session,
        Model model
    ) {
        // 사용자 인증 로직
        User user = userService.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loginUser", user); // 사용자 정보 저장
            return "redirect:/";
        } else {
            model.addAttribute("error", "이메일 또는 비밀번호가 일치하지 않습니다.");
            return "login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }


}
