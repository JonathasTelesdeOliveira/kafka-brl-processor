package com.uploadBRL.jhonatanTeles.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AggregateInDTO {
    @JsonProperty("a")
    private Integer tradeId;
    @JsonProperty("q")
    private BigDecimal quantity;
    @JsonProperty("p")
    private BigDecimal price;
    @JsonProperty("T")
    private Long dataSped;
    @JsonProperty("f")
    private Integer firstTradeld;
    @JsonProperty("l")
    private Integer LastTradeld;
}










