package com.github.fabriciolfj.contaservice.api.controller;

import com.github.fabriciolfj.contaservice.api.dto.request.LimiteRequest;
import com.github.fabriciolfj.contaservice.domain.service.LimiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/limites")
@RequiredArgsConstructor
public class LimiteController {

    private final LimiteService limiteService;

    @PostMapping("/{conta}")
    public Mono<?> setLimite(@RequestBody final LimiteRequest request, @PathVariable("conta") final String conta) {
        return limiteService.process(conta, request);
    }
}
