package com.github.fabriciolfj.contaservice.domain.service;

import com.github.fabriciolfj.contaservice.api.exceptions.DomainBusinessException;
import com.github.fabriciolfj.contaservice.domain.entity.Banco;
import com.github.fabriciolfj.contaservice.domain.repository.BancoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class BancoService {

    private final BancoRepository bancoRepository;

    public Mono<Banco> findByCode(final String code) {
        return bancoRepository.findByCode(code)
                .switchIfEmpty(Mono.error(new DomainBusinessException("Banco não encontrado para code: " + code)));
    }

    public Mono<Banco> findById(final String id) {
        return bancoRepository.findById(id)
                .switchIfEmpty(Mono.error(new DomainBusinessException("Banco não encontrado para o id: " + id)));
    }
}
