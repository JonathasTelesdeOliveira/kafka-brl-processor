package com.uploadBRL.jhonatanTeles.config;


import com.uploadBRL.jhonatanTeles.dto.out.TransacaoKafkaDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, List<TransacaoKafkaDTO>> producerFactory() {
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
    @Bean
    public KafkaTemplate<String, List<TransacaoKafkaDTO>> kafkaTemplate(
            ProducerFactory<String, List<TransacaoKafkaDTO>> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
















