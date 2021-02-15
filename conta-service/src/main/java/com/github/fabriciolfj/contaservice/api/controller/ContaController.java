package com.github.fabriciolfj.contaservice.api.controller;

import com.github.fabriciolfj.contaservice.api.dto.request.OperacaoRequest;
import com.github.fabriciolfj.contaservice.api.dto.response.ContaResponse;
import com.github.fabriciolfj.contaservice.domain.service.ContaService;
import com.github.fabriciolfj.contaservice.domain.service.OperacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService contaService;
    private final OperacaoService operacaoService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<ContaResponse> list() {
        return contaService.findAll();
    }

    @GetMapping("/{numero}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<ContaResponse> find(@PathVariable("numero") final String numero) {
        return contaService.findNumero(numero);
    }

    @PostMapping("/operacao/{numero}")
    public Mono<?> operar(@RequestBody final OperacaoRequest request, @PathVariable("numero") final String conta) {
        return operacaoService.execute(request, conta)
                .flatMap(r -> Mono.empty());
    }

}

