package com.github.fabriciolfj.limiteservice.adapters.persistence;

import com.github.fabriciolfj.limiteservice.core.document.Limite;
import com.github.fabriciolfj.limiteservice.core.port.out.LimiteBaseOut;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface LimiteRepository extends ReactiveMongoRepository<Limite, String>, LimiteBaseOut {

    Mono<Limite> findByContaComDigito(final String conta);

    Mono<Limite> save(final Limite limite);
}
