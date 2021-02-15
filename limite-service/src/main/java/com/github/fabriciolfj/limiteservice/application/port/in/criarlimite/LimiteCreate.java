package com.github.fabriciolfj.limiteservice.application.port.in.criarlimite;

import com.github.fabriciolfj.limiteservice.application.domain.document.Limite;
import reactor.core.publisher.Mono;

public interface LimiteCreate {

    Mono<Limite> execute(final Limite limite);
}
