package com.teamzipup.zipup.service;

import com.teamzipup.zipup.mapper.ProductMapper;
import com.teamzipup.zipup.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void insertProduct(
            long id,
            String sellerId,
            MultipartFile image,
            String productName,
            int price,
            String option1,
            String option2,
            String option3,
            String category,
            MultipartFile description) {
        Properties properties = System.getProperties(); //프로퍼티 이용해서 기본 파일 저장 경로 가져오기
        String productDir = System.getProperty("user.dir") + "/src/main/resources/static/images/product_images/"; //product_images 폴더 내 썸네일이미지 들어감 설정
        String descriptionDir = System.getProperty("user.dir") + "/src/main/resources/static/images/product_description/"; //product_description 폴더 내 상세이미지 들어감 설정
        System.out.println("productDir : " + productDir); //경로 출력
        System.out.println("descriptionDir : " + descriptionDir); //경로 출력

        File imgFolder = new File(productDir); //product_images 폴더 생성
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }

        // 이미지 파일 이름 가져오기
        String imageName = image.getOriginalFilename();
        String descriptionName = description.getOriginalFilename();

        //이미지 저장할 경로 + 이미지 이름
        File imageFile = new File(productDir + imageName);
        File descriptionFile = new File(descriptionDir + descriptionName);

        //이미지 저장
        try {
            image.transferTo(imageFile);
            description.transferTo(descriptionFile);

            Product product = new Product();
            product.setId(id);
            product.setSellerId(sellerId);
            product.setProductName(productName);
            product.setPrice(price);
            product.setOption1(option1);
            product.setOption2(option2);
            product.setOption3(option3);
            product.setCategory(category);
            product.setDescription(descriptionName);

            product.setImage("/images/product_images/" + imageName);
            product.setDescription("/images/product_description/" + descriptionName);

            productMapper.insertProduct(product);
            System.out.println("파일 업로드 완료");
            System.out.println("썸네일 저장경로 : " + imageFile.getAbsolutePath());
            System.out.println("상세이미지 저장경로 : " + descriptionFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(e.getMessage() + "이미지 저장 중 문제가 발생했습니다.");
        }
    }
}
