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
                             MultipartFile image,
                             @RequestParam("productName") String productName,
                             @RequestParam("price") int price,
                             @RequestParam("option1") String option1,
                             @RequestParam("option2") String option2,
                             @RequestParam("option3") String option3,
                             @RequestParam("category") String category,
                             MultipartFile description,
                             HttpSession session,
                             Model model) {
        // 세션에서 로그인된 사용자 정보 가져오기
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login"; // 로그인 페이지로 이동
        }

        // 역할 확인
        if (!"SELLER".equals(loginUser.getRole())) {
            model.addAttribute("error", "상품 등록 권한이 없습니다.");
            return "index"; // 권한 없음 페이지로 이동
        }

        long sellerId = loginUser.getId(); // 세션에서 가져온 사용자 ID

        // 상품 등록 로직 호출
        productService.insertProduct(id, sellerId, image, productName, price, option1, option2, option3, category, description);

        model.addAttribute("msg", "상품 등록이 완료되었습니다.");
        return "redirect:/product/Detail/" + id; // 등록된 productDetail.html로 이동
    }
}