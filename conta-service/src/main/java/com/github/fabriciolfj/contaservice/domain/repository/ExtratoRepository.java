package com.github.fabriciolfj.contaservice.domain.repository;

import com.github.fabriciolfj.contaservice.domain.entity.Extrato;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ExtratoRepository extends ReactiveCrudRepository<Extrato, Long> {
}
