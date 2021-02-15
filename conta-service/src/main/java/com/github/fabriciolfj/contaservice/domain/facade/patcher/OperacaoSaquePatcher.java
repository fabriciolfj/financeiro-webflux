package com.github.fabriciolfj.contaservice.domain.facade.patcher;

import com.github.fabriciolfj.contaservice.api.dto.request.OperacaoRequest;
import com.github.fabriciolfj.contaservice.api.exceptions.DomainBusinessException;
import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import com.github.fabriciolfj.contaservice.domain.entity.Extrato;
import com.github.fabriciolfj.contaservice.domain.repository.ExtratoRepository;
import com.github.fabriciolfj.contaservice.domain.validations.MovimentacaoValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OperacaoSaquePatcher {

    private final ExtratoRepository extratoRepository;
    private final List<MovimentacaoValidation> validacoes;

    public Mono<?> execute(final OperacaoRequest request, final Conta conta) {
        return getUltimoRegistroExtrato(conta)
                .flatMap(c -> executeValidacoes(request, c))
                .flatMap(c ->  Mono.empty());
    }

    private Mono<?> executeValidacoes(OperacaoRequest request, Extrato c) {
        return Flux.fromStream(validacoes.stream())
                .flatMap(v -> v.execute(request, c))
                .flatMap(result -> {
                    log.info(String.valueOf(result.getT1()));
                    log.info(String.valueOf(result.getT2()));
                    return Mono.empty();
                })
                .then().log();
    }

    private Mono<Extrato> getUltimoRegistroExtrato(Conta conta) {
        return extratoRepository.findTop1ByContaOrderByData(conta.getId())
                .flatMap(e -> {
                    log.info("Ultimo registro do extrato: {}", e);
                    return Mono.just(e);
                })
                .switchIfEmpty(Mono.defer(() -> Mono.error(new DomainBusinessException("Ultimo registro do extrato, não localizado"))))
                .log();
    }
}
