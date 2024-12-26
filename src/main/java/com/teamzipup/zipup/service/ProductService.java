package com.teamzipup.zipup.service;

import com.teamzipup.zipup.model.Product;

import java.util.List;

public interface ProductService {
    /* ******************판매자 생성 페이지(product/add)********************* */

    // 모든 제품 목록 조회
    List<Product> getAllProduct();

    // 판매자 제품 등록
    void insertProduct(Product product);
}
