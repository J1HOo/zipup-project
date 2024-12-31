package com.teamzipup.zipup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class PageController {

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

    @GetMapping("/purchase/completed")
    public String purchaseCompleted() {
        return "purchaseCompleted"; // purchaseCompleted.html로 이동
    }
}
