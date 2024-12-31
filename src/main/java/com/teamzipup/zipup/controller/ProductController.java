package com.teamzipup.zipup.controller;

import com.teamzipup.zipup.dto.Product;
import com.teamzipup.zipup.dto.User;
import com.teamzipup.zipup.service.ProductService;
import com.teamzipup.zipup.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    /* 메인 페이지 */
    @GetMapping("/")
    public String mainPage(HttpSession session, Model model) {
        // 로그인 사용자 확인
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            model.addAttribute("user", loginUser);
        }

        // 상품 리스트 가져오기
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "index"; // 메인 페이지
    }

    /* 전체 상품 리스트 페이지 */
    @GetMapping("/products")
    public String productListPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productList"; // 상품 리스트 페이지
    }

    /* 카테고리별 상품 리스트 페이지 */
    @GetMapping("/products/category")
    public String categoryPage(@RequestParam("category") String category, Model model) {
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("selectedCategory", category);
        return "productList"; // 카테고리별 상품 리스트 템플릿
    }

    /* 상품 등록 페이지 */
    @GetMapping("/product/add")
    public String productAddPage(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        if (!"seller".equals(loginUser.getRole())) {
            model.addAttribute("error", "상품 등록 권한이 없습니다.");
            return "redirect:/";
        }

        return "productAdd"; // 상품 등록 페이지
    }

    /* 상품 등록 요청 처리 */
    @PostMapping("/product/add")
    public String productAdd(@RequestParam("productName") String productName,
                             @RequestParam("price") int price,
                             @RequestParam(value = "option1", required = false) String option1,
                             @RequestParam(value = "option2", required = false) String option2,
                             @RequestParam(value = "option3", required = false) String option3,
                             @RequestParam("category") String category,
                             @RequestParam("description") MultipartFile description,
                             @RequestParam("image") MultipartFile image,
                             HttpSession session,
                             Model model) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null || !"seller".equals(loginUser.getRole())) {
            model.addAttribute("error", "상품 등록 권한이 없습니다.");
            return "redirect:/login";
        }

        try {
            long sellerId = loginUser.getId();
            long productId = productService.insertProduct(sellerId, image, productName, price, option1, option2, option3, category, description);
            return "redirect:/product/detail/" + productId;
        } catch (Exception e) {
            model.addAttribute("error", "상품 등록 중 문제가 발생했습니다.");
            return "productAdd"; // 상품 등록 페이지로 이동
        }
    }

    /*  상품 상세 페이지 */
    @GetMapping("/product/detail/{id}")
    public String productDetail(@PathVariable("id") long id, HttpSession session, Model model) {
        Product product = productService.getProductById(id);
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login"; // 비회원은 로그인 페이지로 리디렉션
        }

        if (product == null) {
            model.addAttribute("error", "상품을 찾을 수 없습니다.");
            return "redirect:/";
        }

        User seller = userService.getUserById(product.getSellerId());
        if (seller == null) {
            model.addAttribute("error", "판매자 정보를 찾을 수 없습니다.");
            return "index";
        }

        String formattedPrice = NumberFormat.getNumberInstance(Locale.KOREA).format(product.getPrice());
        model.addAttribute("product", product);
        model.addAttribute("formattedPrice", formattedPrice);

        List<String> option1List = product.getOption1() != null ? List.of(product.getOption1().split(",")) : List.of();
        List<String> option2List = product.getOption2() != null ? List.of(product.getOption2().split(",")) : List.of();

        model.addAttribute("product", product);
        model.addAttribute("companyName", seller.getCompanyName());
        model.addAttribute("option1List", option1List);
        model.addAttribute("option2List", option2List);
        model.addAttribute("description", product.getDescription());

        return "productDetail"; // 상세 페이지
    }
}
