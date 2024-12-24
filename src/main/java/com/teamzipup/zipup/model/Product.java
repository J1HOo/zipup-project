package com.teamzipup.zipup.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private long id; //상품아이디
    private String sellerId; //판매자 아이디
    private String image; //이미지
    private String productName; //상품명
    private int price; //상품 가격
    private String option1; //상품 옵션1
    private String option2; //상품 옵션2
    private String option3; //상품 옵션3
    private String category; //카테고리
    private String description; //상품 설명
}
