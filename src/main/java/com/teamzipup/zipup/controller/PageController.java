
package com.teamzipup.zipup.controller;
import com.teamzipup.zipup.model.User;
import com.teamzipup.zipup.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PageController {

    private final UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // index.html로 이동
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html로 이동
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // signup.html로 이동
    }

    @GetMapping("/signup/user")
    public String userSignup() {
        return "userSignup"; // userSignup.html로 이동
    }

    @GetMapping("/signup/seller")
    public String sellerSignup() {
        return "sellerSignup"; // sellerSignup.html로 이동
    }

    @GetMapping("/product/list")
    public String productList() {
        return "productList"; // productList.html로 이동
    }

    @GetMapping("/product/detail")
    public String productDetail() {
        return "productDetail"; // productDetail.html로 이동
    }

    @GetMapping("/product/add")
    public String productAdd() {
        return "productAdd"; // productAdd.html로 이동
    }

    @PostMapping("/")
    public String index(@ModelAttribute("user") User user, Model model) {
        userService.insertUser(user);
        model.addAttribute("msg", "회원가입 성공");
        return "index";
    }
}
