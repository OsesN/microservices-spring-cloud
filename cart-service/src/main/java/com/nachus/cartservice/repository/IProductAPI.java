package com.nachus.cartservice.repository;

import com.nachus.cartservice.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-service")
public interface IProductAPI {
    @GetMapping("/products/{code}")
    public ProductDTO getProductByCode(@PathVariable Long code);
}
