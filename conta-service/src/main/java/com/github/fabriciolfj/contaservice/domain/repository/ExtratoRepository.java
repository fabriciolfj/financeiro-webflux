package com.github.fabriciolfj.contaservice.domain.repository;

import com.github.fabriciolfj.contaservice.domain.entity.Extrato;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ExtratoRepository extends ReactiveCrudRepository<Extrato, Long> {

    @Query("Select * from Extrato where conta_id = :conta and operacao = :operacao")
    Mono<Extrato> findExtrato(final Long conta, final String operacao);
}
