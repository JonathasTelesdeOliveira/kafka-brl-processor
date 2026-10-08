package com.uploadBRL.jhonatanTeles.config;
import com.uploadBRL.jhonatanTeles.dto.out.TransacaoKafkaDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import tools.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, List<TransacaoKafkaDTO>> consumerFactory() {

        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");

        props.put(ConsumerConfig.GROUP_ID_CONFIG, "uploadBRL-t9");

        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        JacksonJsonDeserializer<List<TransacaoKafkaDTO>> deserializer =
                new JacksonJsonDeserializer<>(
                        new TypeReference<List<TransacaoKafkaDTO>>() {}
                );

        deserializer.addTrustedPackages("com.uploadBRL.jhonatanTeles.dto.out");

        return new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(), deserializer);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, List<TransacaoKafkaDTO>>
    kafkaListenerContainerFactory(ConsumerFactory<String, List<TransacaoKafkaDTO>> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, List<TransacaoKafkaDTO>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}





