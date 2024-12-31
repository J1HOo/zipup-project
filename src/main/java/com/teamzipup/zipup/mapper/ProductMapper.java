package com.teamzipup.zipup.mapper;

import com.teamzipup.zipup.dto.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    // 상품 전체 리스트
    List<Product> findAll();

    // 상품 카테고리 리스트
    List<Product> findByCategory(String category);

    // 판매자 제품 등록
    void insertProduct(Product product);

    // 상품 ID로 조회
    Product findById(long id);
}