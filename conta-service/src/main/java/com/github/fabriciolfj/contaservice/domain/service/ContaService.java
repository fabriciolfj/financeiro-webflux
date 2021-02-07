package com.github.fabriciolfj.contaservice.domain.service;

import com.github.fabriciolfj.contaservice.api.dto.mapper.ContaMapper;
import com.github.fabriciolfj.contaservice.api.dto.request.ContaRequest;
import com.github.fabriciolfj.contaservice.api.dto.response.ContaResponse;

import com.github.fabriciolfj.contaservice.api.exceptions.ContaNotExistsException;
import com.github.fabriciolfj.contaservice.api.exceptions.ListaContaException;
import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import com.github.fabriciolfj.contaservice.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContaService {

    private final BancoService bancoService;
    private final ContaRepository contaRepository;
    private final ContaMapper contaMapper;

    public Mono<Conta> save(final ContaRequest request) {
        return contaRepository.findByNumero(request.getNumero())
                .switchIfEmpty(Mono.defer(() -> createConta(request)));
    }

    private Mono<Conta> createConta(ContaRequest request) {
        return bancoService.findByCode(request.getCodeBanco())
                .map(b -> {
                    var conta = contaMapper.toEntity(request);
                    conta.setBanco(b.getId());
                    return conta;
                }).flatMap(c -> contaRepository.save(c).log())
                .log();
    }

    public Flux<ContaResponse> findAll() {
        return contaRepository
                .findAll()
                .flatMap(this::getContaResponse)
                .doOnError(t -> {
                    throw new ListaContaException("Falha ao listar as contas. Detalhes: " + t.getMessage());
                });
    }


    public Mono<ContaResponse> findNumero(final String numero) {
        return contaRepository.findByNumero(numero)
                .flatMap(this::getContaResponse)
                .switchIfEmpty(Mono.error(new ContaNotExistsException("Conta não encontrada para o numero: " + numero)));
    }

    private Mono<ContaResponse> getContaResponse(Conta conta) {
        return bancoService.findById(conta.getId())
                .map(b -> {
                    log.info("Banco localizado: {}", b.toString());
                    var response = contaMapper.toResponse(conta);
                    response.setBanco(b.getCode());
                    return response;
                });
    }
}
