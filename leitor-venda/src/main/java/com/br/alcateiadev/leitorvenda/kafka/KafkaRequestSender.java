package com.br.alcateiadev.leitorvenda.kafka;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaRequestSender {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicRequest, Object request) {
        Message<String> message = MessageBuilder
                .withPayload(new Gson().toJson(request))
                .setHeader(KafkaHeaders.TOPIC, topicRequest)
                .build();

        this.kafkaTemplate.send(message);
    }

}
