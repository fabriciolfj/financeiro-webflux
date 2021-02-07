package com.github.fabriciolfj.contaservice.domain.service;

import com.github.fabriciolfj.contaservice.api.dto.request.ContaRequest;
import com.github.fabriciolfj.contaservice.api.dto.response.AberturaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AberturaContaService {

    private final ContaService contaService;
    private final ExtratoService extratoService;

    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<AberturaResponse> execute(final ContaRequest request) {
        return contaService.save(request)
                .flatMap(c -> extratoService.saveAbertura(c, request.getSaldo()))
                .flatMap(e -> Mono.just(AberturaResponse.builder()
                        .mensagem("Registro criado")
                        .build())).log();
    }
}
