package com.github.fabriciolfj.contaservice.domain.repository;

import com.github.fabriciolfj.contaservice.domain.entity.Extrato;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ExtratoRepository extends ReactiveMongoRepository<Extrato, String> {

    Mono<Extrato> findByContaAndOperacao(final String conta, final String operacao);

    Mono<Extrato> findTop1ByContaOrderByData(final String conta);
}
