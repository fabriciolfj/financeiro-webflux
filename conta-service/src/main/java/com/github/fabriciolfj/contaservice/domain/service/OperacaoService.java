package com.github.fabriciolfj.contaservice.domain.service;

import com.github.fabriciolfj.contaservice.api.dto.request.OperacaoRequest;
import com.github.fabriciolfj.contaservice.domain.entity.TipoOperacao;
import com.github.fabriciolfj.contaservice.domain.facade.patcher.OperacaoSaquePatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class OperacaoService {

    private final OperacaoSaquePatcher operacaoSaque;
    private final ContaService contaService;

    public Mono<?> execute(final OperacaoRequest request, final String conta) {
        return Mono.just(request.getTipo())
                .map(value -> TipoOperacao.getOperacao(value))
                .flatMap(tipo -> {
                    if (tipo.getDescricao().equals(TipoOperacao.SAQUE.getDescricao())) {
                        return processar(request, conta);
                    }

                    return Mono.empty();
                });
    }

    private Mono<?> processar(final OperacaoRequest request, final String conta) {
        return contaService.findConta(conta)
                .flatMap(c -> operacaoSaque.execute(request, c));
    }
}
