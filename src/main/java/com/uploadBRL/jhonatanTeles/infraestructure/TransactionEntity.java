package com.uploadBRL.jhonatanTeles.infraestructure;

import com.uploadBRL.jhonatanTeles.enums.Lado;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.PastOrPresent;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transacoes")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TradeId")
    private Integer tradeId;

    @PastOrPresent(message = "Insira uma data válida.")
    @Column(name = "DATA_COMPRA")
    private LocalDateTime dataSped;

    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name = "BUY_SELL")
    private Lado lado;

    @Column(name = "PRICE", precision = 18, scale = 8)
    private BigDecimal price;

    @Column(name = "TOTAL_SPENT", precision = 18, scale = 8)
    private BigDecimal quantycoinExe;

    @Column(name = "QUANTIDADE", precision = 18, scale = 8)
    private BigDecimal quantityDol;

    @Column(name = "SYMBOL_QUANTIDADE")
    private String symbolquantity;

    @Column(name = "FREE", precision = 18, scale = 8)
    private BigDecimal taxa;

    @Column(name = "Simbolo_TAXA")
    private String symboltaxa;





    @Column(name = "VALOR_BRUTO", precision = 18, scale = 8)
    private BigDecimal valorBruto;

    @Column(name = "VALOR_LIQUIDO", precision = 18, scale = 8)
    private BigDecimal valorLiquido;

    @Column(name = "VALOR_BRL", precision = 18, scale = 8)
    private BigDecimal valorBrl;

    @Column(name = "DATA_COMPRA_USDTBRL")
    private LocalDateTime dataSpedUSDTBRL;

    @Column(name = "valor_bruto_brl", precision = 18, scale = 8)
    private BigDecimal valorBrutoBrl;

    @Column(name = "valor_liquido_brl", precision = 18, scale = 8)
    private BigDecimal valorLiquidoBrl;

    @PrePersist
    @PreUpdate
    public void updadeSymbol(){
        if(symbol != null) {
            setSymbol(symbol.toUpperCase(Locale.ROOT));
        }
    }
}

