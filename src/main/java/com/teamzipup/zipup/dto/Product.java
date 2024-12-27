package com.teamzipup.zipup.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //상품아이디
    private String sellerId; //판매자 아이디
    private String image; //이미지
    private String productName; //상품명
    private int price; //상품 가격
    private String option1; //상품 옵션1 (색상)
    private String option2; //상품 옵션2 (사이즈)
    private String option3; //상품 옵션3 (옵션 없음)
    private String category; //카테고리
    private String description; //상품 설명
}

