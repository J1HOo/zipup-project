package com.teamzipup.zipup.controller;

import com.teamzipup.zipup.service.ProductService;
import com.teamzipup.zipup.dto.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/add")
    public String productAdd(@RequestParam("id") long id,
                             @RequestParam("productName") String productName,
                             @RequestParam("price") int price,
                             @RequestParam("option1") String option1,
                             @RequestParam("option2") String option2,
                             @RequestParam("option3") String option3,
                             @RequestParam("category") String category,
                             @RequestParam("description") MultipartFile description,
                             @RequestParam("image") MultipartFile image,
                             HttpSession session,
                             Model model) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        if (!"SELLER".equals(loginUser.getRole())) {
            model.addAttribute("error", "상품 등록 권한이 없습니다.");
            return "redirect:/";
        }

        long sellerId = loginUser.getId();

        // 서비스 호출
        productService.insertProduct(id, sellerId, image, productName, price, option1, option2, option3, category, description);

        model.addAttribute("msg", "상품 등록이 완료되었습니다.");
        return "redirect:/product/Detail/" + id;
    }

}