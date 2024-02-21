package com.nacho.productsservice.controller;

import com.nacho.productsservice.DTO.ProductDTO;
import com.nacho.productsservice.model.Product;
import com.nacho.productsservice.service.IProductService;
import org.apache.coyote.Response;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private IProductService productService;


    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<ProductDTO> productsDTO=productService.getProducts().stream()
                .map(product -> {
                    try {
                        return ProductDTO.builder()
                                .code(product.getCode())
                                .name(product.getName())
                                .brand(product.getBrand())
                                .price(product.getPrice())
                                .build();
                    } catch (Exception e) {
                        return null;
                    }
                }).toList();

        return ResponseEntity.ok(productsDTO);
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getProductByCode(@PathVariable Long code){
        Product product=productService.getProductByCode(code);
        try {
            ProductDTO productDTO= ProductDTO.builder()
                    .code(product.getCode())
                    .name(product.getName())
                    .brand(product.getBrand())
                    .price(product.getPrice())
                    .build();
            return ResponseEntity.ok(productDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produt not found");

        }
    }


}
