package com.teamzipup.zipup.controller;

import com.teamzipup.zipup.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/add")
    public String productAdd(@RequestParam("id")long id,
                             @RequestParam("sellerId") String sellerId,
                             MultipartFile image,
                             @RequestParam("productName") String productName,
                             @RequestParam("price") int price,
                             @RequestParam("option1") String option1,
                             @RequestParam("option2") String option2,
                             @RequestParam("option3") String option3,
                             @RequestParam("category") String category,
                             MultipartFile description, Model model) {

        productService.insertProduct(id, sellerId, image, productName, price, option1, option2, option3, category, description);

        model.addAttribute("msg", "상품 등록이 완료되었습니다.");
        return "product/Detail/{id}"; // 등록된 productDetail.html로 이동
    }
}
