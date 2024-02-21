package com.nachus.cartservice.controller;

import com.nachus.cartservice.DTO.CartDTO;
import com.nachus.cartservice.DTO.ProductDTO;
import com.nachus.cartservice.Model.Cart;
import com.nachus.cartservice.repository.IProductAPI;
import com.nachus.cartservice.service.ICartService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carts")
@CrossOrigin
public class CartController {
    @Autowired
    private ICartService cartService;

    @Autowired
    private IProductAPI productAPI;

    @GetMapping("/{id}")
    @CircuitBreaker(name = "products-service",fallbackMethod = "getCartFallbackMethod")
    @Retry(name="products-service")
    public ResponseEntity<?> getCart(@PathVariable Long id){
        Cart cart=cartService.getCartById(id);
        List<ProductDTO> productDTOList=new ArrayList<>();

        for(Long codeProduct:cart.getProductsList()){
            productDTOList.add(productAPI.getProductByCode(codeProduct));
        }
        CartDTO cartDTO= CartDTO.builder()
                .id_cart(cart.getId_cart())
                .productsList(productDTOList)
                .totalPrice(cart.getTotalPrice())
                .build();

        return ResponseEntity.ok(cartDTO);
    }

    @PutMapping("/add/{id}/{code}")
    public ResponseEntity<?> addProductByCode(@PathVariable Long code,@PathVariable Long id){
        Cart cartUpdated=cartService.addProductByCode(id,code);
        return ResponseEntity.ok(cartUpdated);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCart(@RequestBody Cart cart){
            System.out.println(cart);
            Cart cartCreated=cartService.createCart(cart);

            URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(cartCreated.getId_cart()).toUriString());
            return ResponseEntity.created(uri).body("Cart was created");
    }
    @PutMapping("/remove/{id}/{code}")
    public ResponseEntity<?> removeProductByCode(@PathVariable Long code, @PathVariable Long id){
        Cart cartUpdated=cartService.removeProductByCode(id,code);
        return ResponseEntity.status(HttpStatus.OK).body(cartUpdated);

    }

    private CartDTO getCartFallbackMethod(Throwable throwable){
        List<ProductDTO> listProductDTO=new ArrayList<>();
        listProductDTO.add(ProductDTO.builder()
                .brand("ERROR")
                .price(9999999.)
                .name("ERROR")
                .code(999999L)
                .img("ERROR")
                .build());

        return CartDTO.builder()
                .totalPrice(9999999.)
                .id_cart(999999999L)
                .productsList(listProductDTO)
                .build();


    }
    private void createException(){
        throw new IllegalArgumentException("Error");
    }
    
}
