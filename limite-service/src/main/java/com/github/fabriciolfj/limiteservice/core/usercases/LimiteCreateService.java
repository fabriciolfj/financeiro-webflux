package com.github.fabriciolfj.limiteservice.core.usercases;

import com.github.fabriciolfj.limiteservice.core.exceptions.DomainBusinessException;
import com.github.fabriciolfj.limiteservice.core.document.Limite;
import com.github.fabriciolfj.limiteservice.core.port.in.LimiteCreate;
import com.github.fabriciolfj.limiteservice.core.port.out.LimiteBaseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimiteCreateService implements LimiteCreate {

    private final LimiteBaseOut limiteBaseOut;

    @Override
    public Mono<?> execute(final Limite limite) {
        log.info("Salvando o limite: {}", limite);
        return limiteBaseOut.findConta(limite.getContaComDigito())
                .flatMap(r -> {
                    log.error("Limite ja cadastrado para conta: {}", limite.getContaComDigito());
                    return Mono.error(new DomainBusinessException("Limite ja cadastrado para conta: " + limite.getContaComDigito()));
                })
                .switchIfEmpty(Mono.defer(() -> limiteBaseOut.save(limite)));
    }
}
