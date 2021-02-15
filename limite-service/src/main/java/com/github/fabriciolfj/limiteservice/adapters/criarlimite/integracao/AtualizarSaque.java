package com.github.fabriciolfj.limiteservice.adapters.criarlimite.integracao;

import com.github.fabriciolfj.limiteservice.adapters.criarlimite.integracao.dto.AtualizarContaDTO;
import com.github.fabriciolfj.limiteservice.adapters.criarlimite.mapper.LimiteMapper;
import com.github.fabriciolfj.limiteservice.application.domain.criarlimite.LimiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class AtualizarSaque {

    private final LimiteService limiteService;
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
                .flatMap(doc -> limiteService.execute(doc))
                .doOnError(e ->  log.error("Falha ao salvar o limite {}, detalhes {}", value, e.getMessage()))
        .subscribe();
    }

}
