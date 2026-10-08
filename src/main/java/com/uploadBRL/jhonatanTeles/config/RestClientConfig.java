package com.uploadBRL.jhonatanTeles.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() { // <-- Nome do bean: restClient
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(5000);

        return RestClient.builder()
                .baseUrl("https://api-gcp.binance.com")
                .requestFactory(requestFactory)
                .build();
    }
}








