package com.github.fabriciolfj.limiteservice.application.port.in.repository;

import com.github.fabriciolfj.limiteservice.application.domain.document.LimiteUsoDiario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LimiteUsoRepository extends ReactiveMongoRepository<LimiteUsoDiario, String> {
}
