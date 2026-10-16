package com.uploadBRL.jhonatanTeles.config;

import com.uploadBRL.jhonatanTeles.exeptions.ConflictException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeingError implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Override
    public Exception decode(String s, Response response) {
        int status = response.status();
        if (status == 429) {
            return new ConflictException("Limite de requisições da API da Binance excedido (Rate Limit / 429).");
        } else if (status >= 400 && status < 500) {
            return new ConflictException("Erro na requisição enviada à Binance. Status: " + status);
        } else if (status >= 500) {
            return new ConflictException("A API da Binance está indisponível no momento. Status: " + status);
        } else {
            return defaultErrorDecoder.decode(s, response);
        }
    }
}

