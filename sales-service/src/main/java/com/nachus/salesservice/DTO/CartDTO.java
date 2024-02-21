package com.nachus.salesservice.DTO;

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
