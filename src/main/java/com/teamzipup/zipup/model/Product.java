package com.teamzipup.zipup.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product {

    private String id;             //상품 아이디
    private String sellerid;       //판매자 아이디
    private String image;          //상품 이미지
    private String productName;    //상품 명
    private double price;          //상품 가격
    private String option1;        //상품 옵션 1
    private String option2;        //상품 옵션 2
    private String option3;        //상품 옵션 3
    private String category;       //상품 카테고리
    private String description;    //이미지 주소
}