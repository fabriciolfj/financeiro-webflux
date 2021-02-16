package com.github.fabriciolfj.limiteservice.application.domain.criarlimite;

import com.github.fabriciolfj.limiteservice.adapters.out.exceptions.DomainBusinessException;
import com.github.fabriciolfj.limiteservice.application.domain.document.Limite;
import com.github.fabriciolfj.limiteservice.application.port.in.criarlimite.LimiteCreate;
import com.github.fabriciolfj.limiteservice.application.port.in.repository.LimiteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimiteService implements LimiteCreate {

    private final LimiteRepository limiteRepository;

    @Override
    public Mono<?> execute(final Limite limite) {
        log.info("Salvando o limite: {}", limite);
        return limiteRepository.findByContaComDigito(limite.getContaComDigito())
                .flatMap(r -> {
                    log.error("Limite ja cadastrado para conta: {}", limite.getContaComDigito());
                    return Mono.error(new DomainBusinessException("Limite ja cadastrado para conta: " + limite.getContaComDigito()));
                })
                .switchIfEmpty(Mono.defer(() -> limiteRepository.save(limite)));
    }
}
