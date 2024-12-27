package com.teamzipup.zipup.controller;


import com.teamzipup.zipup.dto.User;
import com.teamzipup.zipup.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // 이용자 회원가입
    @PostMapping("/signup/user")
    public String userSignup(@ModelAttribute("user") User user, Model model) {
        user.setRole("user"); // 이용자 역할 설정
        userService.insertUser(user);
        model.addAttribute("msg", "회원가입 성공 (이용자)");
        return "index";
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
        return "redirect:/login";
    }
}
