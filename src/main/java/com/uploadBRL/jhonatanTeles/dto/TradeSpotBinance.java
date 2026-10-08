package com.uploadBRL.jhonatanTeles.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeSpotBinance {

    @JsonProperty("id")
private Long id;
    @JsonProperty("tempo")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private LocalDateTime tempo;
    @JsonProperty("par")
private String par;
    @JsonProperty("lado")
private String lado;
    @JsonProperty("price")
private BigDecimal price;
    @JsonProperty("executado")
private BigDecimal executado;
    @JsonProperty("symbol_executado")
private String symbol_executado;
    @JsonProperty("quantity")
private BigDecimal quantity;
    @JsonProperty("symbol_quantity")
private String symbol_quantity;
    @JsonProperty("taxa")
private  BigDecimal taxa;
    @JsonProperty("symbol_taxa")
private String symbol_taxa;
}







