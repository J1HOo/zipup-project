package com.teamzipup.zipup.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

  
@Controller
public class PageController {

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
}
