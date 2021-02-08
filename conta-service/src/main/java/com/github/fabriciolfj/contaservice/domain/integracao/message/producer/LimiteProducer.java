package com.github.fabriciolfj.contaservice.domain.integracao.message.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.contaservice.api.exceptions.DomainBusinessException;
import com.github.fabriciolfj.contaservice.domain.integracao.message.dto.AtualizarContaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LimiteProducer {

    @Autowired
    private StreamBridge streamBridge;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String TOPIC_LIMITE = "limite-topic";

    @Transactional("chainedTransactionManager")
    public Mono<Void> process(final AtualizarContaDTO atualizarContaDTO)  {
        try {
            log.info("Enviando a mensagem para o serviço limite: {}", atualizarContaDTO);
            var json = objectMapper.writeValueAsString(atualizarContaDTO);
            var msg = MessageBuilder.withPayload(json)
                    .build();
            streamBridge.send(TOPIC_LIMITE, msg);
            return Mono.empty();
        } catch (Exception e) {
            return Mono.error(new DomainBusinessException("Falha no envio da mensagem para o topic: " + TOPIC_LIMITE));
        }
    }

    /*public void process(final UsoContaDTO usoContaDTO) throws JsonProcessingException {
        try {
            log.info("Enviando a mensagem de uso para o serviço limite: {}", usoContaDTO);
            var json = objectMapper.writeValueAsString(usoContaDTO);
            var msg = MessageBuilder.withPayload(json)
                    .setHeader(KafkaHeaders.TOPIC, topicUso)
                    .build();
            kafkaTemplate.send(msg);
        } catch (Exception e) {
            throw new DomainBusinessException("Falha no envio da mensagem para o topic: " + topicUso);
        }
    }*/
}
