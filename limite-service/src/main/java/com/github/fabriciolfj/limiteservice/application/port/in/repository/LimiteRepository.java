package com.github.fabriciolfj.limiteservice.application.port.in.repository;

import com.github.fabriciolfj.limiteservice.application.domain.document.Limite;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LimiteRepository extends ReactiveMongoRepository<Limite, String> {
}
