package com.uploadBRL.jhonatanTeles.dto.in;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class USDTBRL {
    private BigDecimal price;
    private Long dataSpedUSDTBRL;
}





