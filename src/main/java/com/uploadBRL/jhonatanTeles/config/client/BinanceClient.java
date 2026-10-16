package com.uploadBRL.jhonatanTeles.config.client;


import com.uploadBRL.jhonatanTeles.config.FeingError;
import com.uploadBRL.jhonatanTeles.dto.in.AggregateInDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(
        name = "binanceClient",
        url = "https://api-gcp.binance.com",
        configuration = FeingError.class
)
public interface BinanceClient {
    @GetMapping("/api/v3/aggTrades")
    List<AggregateInDTO> buscarAggTrades(
            @RequestParam("symbol") String symbol,
            @RequestParam("startTime") long startTime,
            @RequestParam("endTime") long endTime
    );
}
