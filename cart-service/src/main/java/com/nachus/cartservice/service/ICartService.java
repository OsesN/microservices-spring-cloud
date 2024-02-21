package com.nachus.cartservice.service;

import com.nachus.cartservice.Model.Cart;

public interface ICartService {
    public Cart createCart(Cart cart);
    public String removeCartById(Long id);
    public Cart getCartById(Long id);

    public Cart removeProductByCode(Long id_cart, Long code);

    Cart addProductByCode(Long id_cart, Long code);

    Cart updateCart(Cart cart);
}
