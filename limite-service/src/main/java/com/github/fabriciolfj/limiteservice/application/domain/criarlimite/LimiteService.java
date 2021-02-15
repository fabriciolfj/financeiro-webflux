package com.github.fabriciolfj.limiteservice.application.domain.criarlimite;

import com.github.fabriciolfj.limiteservice.application.domain.document.Limite;
import com.github.fabriciolfj.limiteservice.application.port.in.criarlimite.LimiteCreate;
import com.github.fabriciolfj.limiteservice.application.port.in.repository.LimiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LimiteService implements LimiteCreate {

    private final LimiteRepository limiteRepository;

    @Override
    public Mono<Limite> execute(final Limite limite) {
        return limiteRepository.save(limite);
    }
}
