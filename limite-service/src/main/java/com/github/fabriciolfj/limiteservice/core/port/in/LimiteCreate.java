package com.github.fabriciolfj.limiteservice.core.port.in;

import com.github.fabriciolfj.limiteservice.core.document.Limite;
import reactor.core.publisher.Mono;

public interface LimiteCreate {

    Mono<?> execute(final Limite limite);
}
