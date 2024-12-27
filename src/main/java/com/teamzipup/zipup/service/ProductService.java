package com.teamzipup.zipup.service;

import com.teamzipup.zipup.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    /* ******************판매자 생성 페이지(product/add)********************* */

    // 판매자 제품 등록
    void insertProduct(
            long id,
            String sellerId,
            MultipartFile image,
            String productName,
            int price,
            String option1,
            String option2,
            String option3,
            String category,
            MultipartFile description
    );
}
