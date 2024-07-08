package com.enigma.kelompok.utils.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortDetailDTO {
    private Integer portofolio_id;
    private Integer stock_id;
    private Integer user_id;
}
