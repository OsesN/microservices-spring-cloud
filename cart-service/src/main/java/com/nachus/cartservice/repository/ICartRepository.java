package com.nachus.cartservice.repository;

import com.nachus.cartservice.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {

}
