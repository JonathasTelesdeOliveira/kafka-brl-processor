package com.uploadBRL.jhonatanTeles.dto.out;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AggregateUSDTBRLOUT {
    private Long id;
    private LocalDateTime dataSped;
    private Integer tradeId;
    private String symbol;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal taxa;
    private BigDecimal valorBrl;
    private LocalDateTime dataSpedUSDTBRL;
}





