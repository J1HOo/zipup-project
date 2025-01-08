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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    // 가격 포매팅
    private List<String> formatPrices(List<Product> products) {
        return (products == null || products.isEmpty())
            ? new ArrayList<>()
            : products.stream().map(product -> String.format("%,d", product.getPrice())).toList();
    }

    /* 메인 페이지 */
    @GetMapping("/")
    public String mainPage(@RequestParam(value = "category", required = false) String category,
                           @RequestParam(value = "searchType", required = false) String searchType,
                           @RequestParam(value = "query", required = false) String query,
                           @RequestParam(value = "sortOrder", required = false) String sortOrder,
                           Model model) {

        // 기본값 설정
        if (category == null) category = "ALL";
        if (searchType == null) searchType = "productName";
        if (sortOrder == null) sortOrder = "random";

        // 랜덤 상품
        if ("ALL".equals(category)) {
            List<Product> todayProducts = productService.getRandomProducts(4);
            model.addAttribute("todayProducts", todayProducts);
            model.addAttribute("formattedTodayPrices", formatPrices(todayProducts));
        } else {
            model.addAttribute("todayProducts", new ArrayList<>());
        }


        // 상품 리스트
        List<Product> products = productService.searchProducts(category, searchType, query, sortOrder);
        model.addAttribute("products", products);
        model.addAttribute("formattedPrices", formatPrices(products));

        // 현재 선택된 옵션 전달
        model.addAttribute("selectedCategory", category);
        model.addAttribute("searchType", searchType);
        model.addAttribute("query", query);
        model.addAttribute("sortOrder", sortOrder);

        return "index";
    }

    /* 전체 상품 리스트 페이지 */
    @GetMapping("/products")
    public String productListPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productList";
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

        return "productAdd";
    }

    /* 상품 등록 요청 처리 */
    @PostMapping("/product/add")
    public String productAdd(@RequestParam("productName") String productName,
                             @RequestParam("price") int price,
                             @RequestParam(value = "option1", required = false) String option1,
                             @RequestParam(value = "option2", required = false) String option2,
                             @RequestParam("category") String category,
                             @RequestParam("description") MultipartFile description,
                             @RequestParam("image") MultipartFile image,
                             HttpSession session,
                             Model model) {
        User loginUser = (User) session.getAttribute("loginUser");

        try {
            long sellerId = loginUser.getId();
            long productId = productService.insertProduct(sellerId, image, productName, price, option1, option2, category, description);
            return "redirect:/product/detail/" + productId;
        } catch (Exception e) {
            model.addAttribute("error", "상품 등록 중 문제가 발생했습니다.");
            return "productAdd";
        }
    }

    /*  상품 상세 페이지 */
    @GetMapping("/product/detail/{id}")
    public String productDetail(@PathVariable("id") long id, HttpSession session, Model model) {
        Product product = productService.getProductById(id);


        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login"; // 비회원은 로그인 페이지로
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
        model.addAttribute("formattedPrice", formattedPrice);

        model.addAttribute("product", product);

        List<String> option1List = product.getOption1() != null ? List.of(product.getOption1().split(",")) : List.of();
        List<String> option2List = product.getOption2() != null ? List.of(product.getOption2().split(",")) : List.of();

        model.addAttribute("product", product);
        model.addAttribute("companyName", seller.getCompanyName());
        model.addAttribute("option1List", option1List);
        model.addAttribute("option2List", option2List);
        model.addAttribute("description", product.getDescription());

        return "productDetail";

    }

    /* 상품 상세 페이지 이동 */
    @GetMapping("/product/{id}")
    public String viewProductDetail(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id); // ID로 상품 조회
        model.addAttribute("product", product);
        return "productDetail";
    }


    @GetMapping("/purchase/completed/{id}")
    public String purchaseCompleted(@PathVariable("id") Long id,
                                    @RequestParam(required = false) String option1,
                                    @RequestParam(required = false) String option2,
                                    HttpSession session,
                                    Model model) {
        // 로그인 사용자 확인
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        // 상품 데이터 조회
        Product product = productService.getProductById(id);
        if (product == null) {
            model.addAttribute("error", "상품 정보를 찾을 수 없습니다.");
            return "redirect:/";
        }

        // 판매자 companyName 가져오기
        User seller = userService.getUserBySellerId(product.getSellerId());
        String sellerCompanyName = seller != null ? seller.getCompanyName() : "알 수 없음";
        System.out.println(sellerCompanyName);
        System.out.println("Seller ID: " + product.getSellerId());
        // 모델에 데이터 추가
        model.addAttribute("userName", loginUser.getUserName());
        model.addAttribute("address", loginUser.getAddress());
        model.addAttribute("companyName", sellerCompanyName);
        System.out.println("companyName" + sellerCompanyName);
        System.out.println("Seller ID: " + product.getSellerId());
        model.addAttribute("product", product);
        model.addAttribute("option1", option1 != null ? option1 : "옵션 없음");
        model.addAttribute("option2", option2 != null ? option2 : "옵션 없음");
        model.addAttribute("formattedPrice", NumberFormat.getNumberInstance(Locale.KOREA).format(product.getPrice()));

        return "purchaseCompleted";
    }
}
