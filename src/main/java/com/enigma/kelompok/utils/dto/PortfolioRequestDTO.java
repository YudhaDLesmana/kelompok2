package com.enigma.kelompok.utils.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioRequestDTO {
    Integer user_id;
    Integer total_amount;
    Integer stock_id;
    Integer quantity_lot;
    Integer portfolio_id;
}
