package com.nacho.productsservice.service;

import com.nacho.productsservice.model.Product;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    public List<Product> getProducts();
    public Product getProductByCode(Long code);

}
