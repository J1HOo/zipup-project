package com.teamzipup.zipup.service;

import com.teamzipup.zipup.mapper.ProductMapper;
import com.teamzipup.zipup.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceUp implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Map<String,Object>> productUsers() {
        List<Product> productList = productMapper.productUsers();
        return productList.stream().map(product -> {
            Map<String,Object> productMap = new HashMap<>();
            productMap.put("id",product.getId());
            productMap.put("sellerid", product.getSellerid());
            productMap.put("image",product.getImage());
            productMap.put("productName",product.getProductName());
            productMap.put("price",product.getPrice());
            productMap.put("option1",product.getOption1());
            productMap.put("option2",product.getOption2());
            productMap.put("option3",product.getOption3());
            productMap.put("category",product.getCategory());
            productMap.put("description",product.getDescription());
            return productMap;
        }).collect(Collectors.toList());
    }

    @Override
    public void insertProduct(Product product) {
        productMapper.insertProduct(product);
    }
}
