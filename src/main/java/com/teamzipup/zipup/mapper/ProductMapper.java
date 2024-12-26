package com.teamzipup.zipup.mapper;

import com.teamzipup.zipup.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface ProductMapper {
    /* ******************판매자 생성 페이지(product/add)********************* */
    // 판매자 제품 등록
    void insertProduct(Product product);

    /* ****************이용자 상세 페이지(product/detail)******************* */
    // 색상 옵션 데이터 불러오기
    String optionColor(int id);

    // 사이즈 옵션 불러오기
    String optionSize(int id);

    // 상세페이지 이미지 불러오기
    String description(int id);

    // 모든 제품 목록 조회
    List<Product> getAllProduct();
}
