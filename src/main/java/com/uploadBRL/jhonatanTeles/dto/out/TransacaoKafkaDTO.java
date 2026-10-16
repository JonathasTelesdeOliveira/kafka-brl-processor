package com.uploadBRL.jhonatanTeles.dto.out;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uploadBRL.jhonatanTeles.enums.Lado;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransacaoKafkaDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("dataSped")
    @JsonAlias({"data_sped", "dataCompra", "data"})
    private LocalDateTime dataSped;
    @JsonProperty("tradeId")
    private Integer tradeId;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("lado")
    private Lado lado;
    @JsonProperty("quantycoinExe")
    private BigDecimal quantycoinExe;
    @JsonProperty("quantityDol")
    private BigDecimal quantityDol;
    @JsonProperty("symbol_quantity_Dol")
    private String symbol_quantity_Dol;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("taxa")
    private BigDecimal taxa;
    @JsonProperty("symbol_taxa")
    private String symbol_taxa;
    @JsonProperty("valorBrl")
    private BigDecimal valorBrl;
    @JsonProperty("dataSpedUSDTBRL")
    private LocalDateTime dataSpedUSDTBRL;
}


