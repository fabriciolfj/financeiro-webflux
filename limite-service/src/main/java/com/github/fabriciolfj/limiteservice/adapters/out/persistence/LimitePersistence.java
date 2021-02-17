package com.github.fabriciolfj.limiteservice.adapters.out.persistence;

import com.github.fabriciolfj.limiteservice.core.document.Limite;
import com.github.fabriciolfj.limiteservice.core.port.out.LimiteBaseOut;
import com.github.fabriciolfj.limiteservice.infra.repository.LimiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LimitePersistence implements LimiteBaseOut {

    private final LimiteRepository limiteRepository;

    @Override
    public Mono<Limite> save(final Limite limite) {
        return limiteRepository.save(limite);
    }

    @Override
    public Mono<Limite> findConta(final String conta) {
        return limiteRepository.findByContaComDigito(conta);
    }
}
