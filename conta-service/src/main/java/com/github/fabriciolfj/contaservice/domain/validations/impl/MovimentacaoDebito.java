package com.github.fabriciolfj.contaservice.domain.validations.impl;

import com.github.fabriciolfj.contaservice.api.dto.request.OperacaoRequest;
import com.github.fabriciolfj.contaservice.api.exceptions.DomainBusinessException;
import com.github.fabriciolfj.contaservice.domain.entity.Extrato;
import com.github.fabriciolfj.contaservice.domain.validations.MovimentacaoValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Slf4j
@Component
public class MovimentacaoDebito implements MovimentacaoValidation {
    @Override
    public Mono<Tuple2<OperacaoRequest, Extrato>> execute(OperacaoRequest request, Extrato extrato) {
        log.info("Saldo: {}, Valor para débito: {}", extrato.getSaldo(), request.getValor());
        if (request.getValor().compareTo(extrato.getSaldo()) > 0) {
            return Mono.error(new DomainBusinessException("Saldo insuficiente para débito."));
        }

        final var tuple2 = Tuples.of(request, extrato);
        return Mono.just(tuple2);
    }
}
