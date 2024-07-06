package com.enigma.kelompok.utils.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortDetailDTO {
    Integer portfolio_id;
    Integer stock_id;
    Integer price;
    Integer quantity_lot;
}
