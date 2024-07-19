package com.start.transaction.Config;

import com.start.transaction.service.kafka.DTO.EmprestimoCadastroDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;



import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, EmprestimoCadastroDTO> emprestimoCadastroDTOconsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        var jsonDeserializer = new JsonDeserializer<>(EmprestimoCadastroDTO.class).trustedPackages("*").forKeys();

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmprestimoCadastroDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EmprestimoCadastroDTO> factory = new ConcurrentKafkaListenerContainerFactory<String,EmprestimoCadastroDTO>();
        factory.setConsumerFactory(emprestimoCadastroDTOconsumerFactory());
        return factory;
    }
}
