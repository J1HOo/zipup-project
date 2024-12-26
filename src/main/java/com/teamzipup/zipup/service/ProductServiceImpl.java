package com.teamzipup.zipup.service;

import com.teamzipup.zipup.mapper.ProductMapper;
import com.teamzipup.zipup.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public java.util.List<Product> getAllProduct() {
        List<Product> productList = productMapper.getAllProduct();
        return productList.stream().map(product -> {
            Map<String, Objects> productMap = new HashMap<>();
            productMap.put("id", Product.getId());
            productMap.put("sellerId", Product.getSellerId());
            productMap.put("image", Product.getImage());
            productMap.put("productName", Product.getProductName());
            productMap.put("price", Product.getPrice());
            productMap.put("option1", Product.getOption1());
            productMap.put("option2", Product.getOption2());
            productMap.put("option3", Product.getOption3());
            productMap.put("category", Product.getCategory());
            productMap.put("description", Product.getDescription());
            return productMap;
        });
    }

    @Override
    public void insertProduct(Product product) {
        productMapper.insertProduct(product);
    }
}
