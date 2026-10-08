package com.uploadBRL.jhonatanTeles.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EnviarApiTransacaoProducerConfig {
    @Bean(name = "apiTransacaoProducerFactory")
    public ProducerFactory<String, Object> apiTransacaoProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        // 1. Endereço do broker do Kafka
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // 2. Serializadores para a Chave e para o Valor (JSON)
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);

        // 3. Desativa envio de tipo no header usando a chave em formato String (evita o erro em vermelho)
        configProps.put("spring.json.add.type.headers", false);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean(name = "apiTransacaoKafkaTemplate")
    public KafkaTemplate<String, Object> apiTransacaoKafkaTemplate() {
       return new KafkaTemplate<>(apiTransacaoProducerFactory());
    }

}



