package com.github.fabriciolfj.contaservice.domain.service;

import com.github.fabriciolfj.contaservice.api.dto.request.ContaRequest;
import com.github.fabriciolfj.contaservice.api.dto.response.AberturaResponse;
import com.github.fabriciolfj.contaservice.api.exceptions.DomainBusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AberturaContaService {

    private final ContaService contaService;
    private final ExtratoService extratoService;

    public Mono<AberturaResponse> execute(final ContaRequest request) {
        return contaService.save(request)
                .flatMap(c -> {
                    log.info("Conta criada: {}", c.toString());
                    return extratoService.saveAbertura(c, request.getSaldo());
                })
                .flatMap(e -> Mono.just(AberturaResponse.builder()
                        .mensagem("Registro criado")
                        .build())).log();
    }
}
