package com.github.fabriciolfj.contaservice.domain.repository;

import com.github.fabriciolfj.contaservice.domain.entity.Banco;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BancoRepository extends ReactiveCrudRepository<Banco, Long> {

    Mono<Banco> findByCode(final String code);
}
