package com.teamzipup.zipup.service;

import com.teamzipup.zipup.dto.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    /* ******************판매자 생성 페이지(product/add)********************* */

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

    Product getProductById(long id); // 상품 ID로 조회
}
