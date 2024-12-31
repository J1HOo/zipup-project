package com.teamzipup.zipup.service;

import com.teamzipup.zipup.dto.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    // 상품 전체 리스트
    List<Product> getAllProducts();

    // 상품 카테고리 리스트
    List<Product> getProductsByCategory(String category);

    // 판매자 제품 등록
    long insertProduct(
            long sellerId,
            MultipartFile image,
            String productName,
            int price,
            String option1,
            String option2,
            String option3,
            String category,
            MultipartFile description
    );

    // 상품 ID로 조회
    Product getProductById(long id);

    List<Product> searchProducts(String productName, String category, Integer minPrice, Integer maxPrice);
}