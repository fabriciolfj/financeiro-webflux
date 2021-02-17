package com.github.fabriciolfj.limiteservice.infra.repository;

import com.github.fabriciolfj.limiteservice.core.document.LimiteUsoDiario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LimiteUsoRepository extends ReactiveMongoRepository<LimiteUsoDiario, String> {
}
