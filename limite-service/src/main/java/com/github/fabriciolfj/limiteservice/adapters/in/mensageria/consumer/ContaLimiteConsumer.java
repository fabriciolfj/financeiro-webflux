package com.github.fabriciolfj.limiteservice.adapters.in.mensageria.consumer;

import com.github.fabriciolfj.limiteservice.adapters.in.mensageria.dto.AtualizarContaDTO;
import com.github.fabriciolfj.limiteservice.adapters.in.mensageria.mapper.LimiteMapper;
import com.github.fabriciolfj.limiteservice.core.port.in.LimiteCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContaLimiteConsumer {

    private final LimiteCreate limiteCreate;
    private final LimiteMapper mapper;
    private Scheduler scheduler = Schedulers.fromExecutor(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

    @Bean
    public Consumer<AtualizarContaDTO> limite() {
        return value -> {
            log.info("Valor consumido: {}", value);
            salvarLimite(value);
        };
    }

    private void salvarLimite(AtualizarContaDTO value) {
        Mono.fromCallable(() -> mapper.toDocument(value))
                .subscribeOn(scheduler)
                .publishOn(Schedulers.boundedElastic())
                .flatMap(doc -> limiteCreate.execute(doc))
                .doOnError(e ->  log.error("Falha ao salvar o limite {}, detalhes {}", value, e.getMessage()))
        .subscribe();
    }

}
