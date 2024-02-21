package com.nachus.cartservice.DTO;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {

    private Long id_cart;
    private List<ProductDTO> productsList;
    private Double totalPrice;

}
