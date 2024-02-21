package com.nachus.cartservice.service;

import com.nachus.cartservice.Model.Cart;
import com.nachus.cartservice.repository.ICartRepository;
import com.nachus.cartservice.repository.IProductAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService{
    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductAPI productAPI;

    @Override
    public Cart createCart(Cart cart) {
        cart.setTotalPrice(calcPrice(cart.getProductsList()));
        return cartRepository.save(cart);
    }

    @Override
    public String removeCartById(Long id) {
        return "Cart removed";
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart removeProductByCode(Long id_cart, Long code) {
        Cart cart=this.getCartById(id_cart);
        List<Long> codeList=cart.getProductsList();
        codeList.remove(code);
        cart.setProductsList(codeList);
        return this.updateCart(cart);

    }
    @Override
    public Cart addProductByCode(Long id_cart,Long code) {
        Cart cart=this.getCartById(id_cart);
        List<Long> codeList=cart.getProductsList();
        codeList.add(code);
        cart.setProductsList(codeList);
        return this.updateCart(cart);

    }

    @Override
    public Cart updateCart(Cart cart) {
        cart.setTotalPrice(calcPrice(cart.getProductsList()));
        return cartRepository.save(cart);
    }

    @CircuitBreaker(name = "products-service",fallbackMethod = "calcPriceFallbackMethod")
    private Double calcPrice(List<Long> codeList){
        Double totalPrice=0.;
        for(Long code:codeList){
            totalPrice+=productAPI.getProductByCode(code).getPrice();
        }
        return totalPrice;
    }
    private Double calcPriceFallbackMethod(){
        return null;
    }
}
