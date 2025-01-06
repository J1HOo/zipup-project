package com.teamzipup.zipup.controller;


import com.teamzipup.zipup.dto.User;
import com.teamzipup.zipup.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

            return "userSignup"; // 회원가입 페이지로 다시 이동
        }
        user.setRole("user"); // 이용자 역할 설정
        userService.insertUser(user);

        return "/login";
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
        return "login";
    }

    @PostMapping("/login")
    public String login(
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        HttpSession session,
        RedirectAttributes redirectAttributes,
        Model model
    ) {
        // 사용자 인증 로직
        User user = userService.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loginUser", user); // 사용자 정보 저장
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error", "이메일 또는 비밀번호가 일치하지 않습니다.");
            return "redirect:/login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // 이메일 찾기
    @GetMapping("/find/email")
    public String findEmailForm() {
        return "findEmail";
    }

    @PostMapping("/find/email")
    public String findEmail(
        @RequestParam("userName") String userName,
        @RequestParam("password") String password,
        Model model,
        RedirectAttributes redirectAttributes
    ) {
        try {
            String email = userService.findEmail(userName, password);
            model.addAttribute("email", email);
            return "findEmailResult";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원 정보를 찾을 수 없습니다.");
            return "redirect:/find/email";
        }
    }

    // 비밀번호 찾기
    @GetMapping("/find/password")
    public String findPasswordForm() {
        return "findPassword";
    }

    @PostMapping("/find/password")
    public String findPassword(
        @RequestParam("email") String email,
        @RequestParam("phoneNumber") String phoneNumber,
        Model model,
        RedirectAttributes redirectAttributes
    ) {
        try {
            String password = userService.findPassword(email, phoneNumber);
            model.addAttribute("password", password);
            return "findPasswordResult";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원 정보를 찾을 수 없습니다.");
            return "redirect:/find/password";
        }
    }

    // 마이 페이지
    @GetMapping("/mypage")
    public String myPage(Model model, HttpSession session){

        User loggedInUser = (User) session.getAttribute("loginUser");
        if(loggedInUser != null){
            model.addAttribute("user", loggedInUser);
        }else{
            model.addAttribute("user", new User());
        }
        return "mypage";
    }


}
