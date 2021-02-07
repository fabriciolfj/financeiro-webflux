package com.github.fabriciolfj.contaservice.api.controller;

import com.github.fabriciolfj.contaservice.api.dto.response.ContaResponse;
import com.github.fabriciolfj.contaservice.domain.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}

