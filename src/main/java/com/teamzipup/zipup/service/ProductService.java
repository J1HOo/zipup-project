package com.teamzipup.zipup.service;

import com.teamzipup.zipup.model.Product;

public interface ProductService {
    /* ******************판매자 생성 페이지(product/add)********************* */
    // 판매자 제품 등록
    void insertProduct(Product product);
}
