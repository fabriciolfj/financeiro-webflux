package com.github.fabriciolfj.contaservice.domain.service;

import com.github.fabriciolfj.contaservice.api.dto.request.LimiteRequest;
import com.github.fabriciolfj.contaservice.domain.facade.create.AtualizarContaCreate;
import com.github.fabriciolfj.contaservice.domain.integracao.message.producer.LimiteProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LimiteService {

    private final AtualizarContaCreate atualizarContaCreate;
    private final ContaService contaService;
    private final LimiteProducer limiteProducer;

    public Mono<Void> process(final String conta, final LimiteRequest request) {
        return contaService.findNumero(conta)
                .flatMap(c  ->  atualizarContaCreate.execute(request, c.getNumero()))
                .flatMap(at -> limiteProducer.process(at));
    }
}
