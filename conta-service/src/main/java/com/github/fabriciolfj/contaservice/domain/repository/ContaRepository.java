package com.github.fabriciolfj.contaservice.domain.repository;

import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ContaRepository extends ReactiveMongoRepository<Conta, String> {

    Mono<Conta> findByNumero(final String numero);
}
