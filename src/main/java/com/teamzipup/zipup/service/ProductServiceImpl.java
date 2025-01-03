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
    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }

    @Override
    public long insertProduct(
            long sellerId,
            MultipartFile image,
            String productName,
            int price,
            String option1,
            String option2,
            String category,
            MultipartFile description) {
        String productDir = System.getProperty("user.dir") + "/src/main/resources/static/images/product_images/";
        String descriptionDir = System.getProperty("user.dir") + "/src/main/resources/static/images/description_images/";

        // 이미지 파일 이름 가져오기
        String imageName = image.getOriginalFilename();
        String descriptionName = description.getOriginalFilename();

        // 이미지 파일 이름 중복 제거
        String uniqueImageName = UUID.randomUUID() + "_" + imageName;
        String uniqueDescriptionName = UUID.randomUUID() + "_" + descriptionName;

        //이미지 저장할 경로 + 이미지 이름
        File imageFile = new File(productDir + uniqueImageName);
        File descriptionFile = new File(descriptionDir + uniqueDescriptionName);

        try {
            if (!imageFile.getParentFile().exists()) {
                imageFile.getParentFile().mkdirs();
            }
            if (!descriptionFile.getParentFile().exists()) {
                descriptionFile.getParentFile().mkdirs();
            }

            image.transferTo(imageFile);
            System.out.println("이미지 저장 완료: " + imageFile.getAbsolutePath());
            description.transferTo(descriptionFile);
            System.out.println("상세페이지 저장 완료: " + descriptionFile.getAbsolutePath());

            Product product = new Product();
            product.setSellerId(sellerId); // 여기서 sellerId 설정
            product.setProductName(productName);
            product.setPrice(price);
            product.setOption1(option1);
            product.setOption2(option2);
            product.setCategory(category);
            product.setImage("/images/product_images/" + uniqueImageName);
            product.setDescription("/images/description_images/" + uniqueDescriptionName);

            productMapper.insertProduct(product); // DB에 데이터 삽입

            System.out.println("상품 등록 성공: " + product);
            return product.getId(); // 생성된 상품 ID 반환
        } catch (IOException e) {
            System.err.println("파일 저장 실패: " + e.getMessage());
            throw new RuntimeException("파일 저장 실패", e);
        }
    }


    // 상품 판매자 조회
    @Override
    public Product getProductById(long id) {
        return productMapper.findById(id);
    }

    // 상품 검색
    public List<Product> searchProducts(String category, String searchType, String query, String sortOrder) {
        return productMapper.searchProducts(category, searchType, query, sortOrder);
    }

    // 오늘의 상품
    public List<Product> getRandomProducts(int count) {
        return productMapper.getRandomProducts(count);
    }

}
