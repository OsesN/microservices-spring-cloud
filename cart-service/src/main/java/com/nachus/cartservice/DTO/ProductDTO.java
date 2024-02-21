package com.nachus.cartservice.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long code;
    private String name;
    private String brand;
    private Double price;
    private String img;
}
