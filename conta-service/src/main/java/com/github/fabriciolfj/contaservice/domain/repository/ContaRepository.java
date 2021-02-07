package com.github.fabriciolfj.contaservice.domain.repository;

import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ContaRepository extends ReactiveCrudRepository<Conta, Long> {

    @Query("Select * from Conta where numero = :numero")
    Mono<Conta> findByNumero(final String numero);
}
