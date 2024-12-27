package com.teamzipup.zipup.service;

import com.teamzipup.zipup.dto.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Map<String,Object>> productUsers();

    void insertProduct(Product product);


}
