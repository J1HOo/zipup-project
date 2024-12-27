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
    public long insertProduct(
        long sellerId,
        MultipartFile image,
        String productName,
        int price,
        String option1,
        String option2,
        String option3,
        String category,
        MultipartFile description) {
        String productDir = System.getProperty("user.dir") + "/src/main/resources/static/images/product_images/";
        String descriptionDir = System.getProperty("user.dir") + "/src/main/resources/static/images/description_images/";

        // 이미지 파일 이름 가져오기
        String imageName = image.getOriginalFilename();
        String descriptionName = description.getOriginalFilename();

        //이미지 저장할 경로 + 이미지 이름
        File imageFile = new File(productDir + imageName);
        File descriptionFile = new File(descriptionDir + descriptionName);


        try {
            if (!imageFile.getParentFile().exists()) imageFile.getParentFile().mkdirs();
            if (!descriptionFile.getParentFile().exists()) descriptionFile.getParentFile().mkdirs();

            image.transferTo(imageFile);
            description.transferTo(descriptionFile);

            Product product = new Product();
            product.setSellerId(sellerId); // 여기서 sellerId 설정
            product.setProductName(productName);
            product.setPrice(price);
            product.setOption1(option1);
            product.setOption2(option2);
            product.setOption3(option3);
            product.setCategory(category);
            product.setImage("/images/product_images/" + imageName);
            product.setDescription("/images/product_description/" + descriptionName);

            productMapper.insertProduct(product); // DB에 데이터 삽입

            System.out.println("상품 등록 성공: " + product);
            return product.getId(); // 생성된 상품 ID 반환
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패: " + e.getMessage(), e);
        }
    }


    @Override
    public Product getProductById(long id) {
        return productMapper.findById(id);
    }
}
