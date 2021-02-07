package com.github.fabriciolfj.contaservice.domain.service;

import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import com.github.fabriciolfj.contaservice.domain.entity.Extrato;
import com.github.fabriciolfj.contaservice.domain.entity.TipoOperacao;
import com.github.fabriciolfj.contaservice.domain.facade.create.ExtratoCreate;
import com.github.fabriciolfj.contaservice.domain.repository.ExtratoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExtratoService {

    private final ExtratoRepository extratoRepository;
    private final ExtratoCreate create;

    public Mono<Extrato> saveAbertura(final Conta conta, final BigDecimal valor) {
        return extratoRepository.findExtrato(conta.getId(), TipoOperacao.ABERTURA.getDescricao())
                .switchIfEmpty(Mono.defer(() -> Mono.just(create.abertura(conta, valor))
                        .flatMap(ex -> extratoRepository.save(ex))));
    }
}
