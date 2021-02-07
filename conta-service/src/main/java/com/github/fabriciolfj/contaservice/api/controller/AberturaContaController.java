package com.github.fabriciolfj.contaservice.api.controller;

import com.github.fabriciolfj.contaservice.api.dto.request.ContaRequest;
import com.github.fabriciolfj.contaservice.api.dto.response.AberturaResponse;
import com.github.fabriciolfj.contaservice.domain.service.AberturaContaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/abertura")
@RequiredArgsConstructor
public class AberturaContaController {

    private final AberturaContaService aberturaContaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AberturaResponse> create(@RequestBody final ContaRequest request) {
        return aberturaContaService.execute(request);
    }
}
