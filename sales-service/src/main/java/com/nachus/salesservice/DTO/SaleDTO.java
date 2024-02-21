package com.nachus.salesservice.DTO;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDTO {
    private Long id_sale;

    private LocalDateTime date;

    private CartDTO cartDTO;
}
