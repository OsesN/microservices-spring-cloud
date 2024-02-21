package com.nachus.salesservice.repository;

import com.nachus.salesservice.DTO.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carts-service")
public interface ICartAPI {
    @GetMapping("/carts/{id}")
    public CartDTO getCartDTOById(@PathVariable Long id);
}
