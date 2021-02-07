package com.github.fabriciolfj.contaservice.domain.service;

import com.github.fabriciolfj.contaservice.api.exceptions.BancoNotExistsException;
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
                .doOnError(t -> {
                    log.info("Banco não encontrado para o code {}. Detalhes: ", code, t.getCause());
                    new BancoNotExistsException("Banco não encontrado para code: " + code);
                });
    }

    public Mono<Banco> findById(final Long id) {
        return bancoRepository.findById(id)
                .doOnError(t -> {
                    log.info("Banco não encontrado para o id {}. Detalhes: ", id, t.getCause());
                    new BancoNotExistsException("Banco não encontrado para o id: " + id);
                });
    }
}
