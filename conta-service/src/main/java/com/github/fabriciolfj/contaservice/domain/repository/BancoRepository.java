package com.github.fabriciolfj.contaservice.domain.repository;

import com.github.fabriciolfj.contaservice.domain.entity.Banco;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BancoRepository extends ReactiveMongoRepository<Banco, String> {

    Mono<Banco> findByCode(final String code);
}
