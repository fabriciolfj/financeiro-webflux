package com.github.fabriciolfj.limiteservice.core.port.out;

import com.github.fabriciolfj.limiteservice.core.document.Limite;
import reactor.core.publisher.Mono;

public interface LimiteBaseOut {

    Mono<Limite> save(final Limite limite);
    Mono<Limite> findByContaComDigito(final String conta);

}
