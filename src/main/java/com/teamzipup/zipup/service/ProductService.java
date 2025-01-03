package com.teamzipup.zipup.service;

import com.teamzipup.zipup.dto.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    // 상품 전체 리스트
    List<Product> getAllProducts();

    // 판매자 제품 등록
    long insertProduct(
            long sellerId,
            MultipartFile image,
            String productName,
            int price,
            String option1,
            String option2,
            String category,
            MultipartFile description
    );

    // 상품 ID로 조회
    Product getProductById(long id);

    // 상품 검색 및 필터링
    List<Product> searchProducts(String category, String searchType, String query, String sortOrder);

    // 오늘의 상품
    List<Product> getRandomProducts(int count);
}