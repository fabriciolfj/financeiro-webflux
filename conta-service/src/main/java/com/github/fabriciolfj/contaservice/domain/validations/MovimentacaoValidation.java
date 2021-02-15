package com.github.fabriciolfj.contaservice.domain.validations;

import com.github.fabriciolfj.contaservice.api.dto.request.OperacaoRequest;
import com.github.fabriciolfj.contaservice.domain.entity.Extrato;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface MovimentacaoValidation {

    Mono<Tuple2<OperacaoRequest, Extrato>> execute(final OperacaoRequest request, final Extrato extrato);
}
