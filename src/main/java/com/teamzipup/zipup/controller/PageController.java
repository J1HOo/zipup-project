package com.teamzipup.zipup.controller;

import com.teamzipup.zipup.model.Product;
import com.teamzipup.zipup.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PageController {
    @Autowired
    private ProductService productService;

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

    @GetMapping("/product/add")
    public String productAdd() {
        return "productAdd"; // product/add로 이동
    }

    @PostMapping("/product/add")
    public String productAdd(@ModelAttribute("product") Product product, Model model) {
        productService.insertProduct(product);
        model.addAttribute("msg", "상품 등록이 완료되었습니다.");
        return "productDetail/${id}"; // 등록된 productDetail.html로 이동
    }

    @GetMapping("/product/detail")
    public String productDetail() {
        return "productDetail"; // productDetail.html로 이동
    }


}
