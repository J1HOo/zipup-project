package com.teamzipup.zipup.mapper;

import com.teamzipup.zipup.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> productUsers();

    void insertProduct(Product product);
}
