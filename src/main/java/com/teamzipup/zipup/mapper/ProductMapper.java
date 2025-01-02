package com.teamzipup.zipup.mapper;

import com.teamzipup.zipup.dto.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    // 상품 전체 리스트
    List<Product> findAll();

    // 판매자 제품 등록
    void insertProduct(Product product);

    // 상품 ID로 조회
    Product findById(long id);

    // 상품 검색 및 정렬
    List<Product> searchProducts(@Param("category") String category,
                                 @Param("searchType") String searchType,
                                 @Param("query") String query,
                                 @Param("sortOrder") String sortOrder);

    // 오늘의 상품
    List<Product> getRandomProducts(int count);
}