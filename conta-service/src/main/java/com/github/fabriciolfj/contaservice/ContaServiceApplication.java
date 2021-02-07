package com.github.fabriciolfj.contaservice;

import com.github.fabriciolfj.contaservice.domain.entity.Banco;
import com.github.fabriciolfj.contaservice.domain.repository.BancoRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class ContaServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ContaServiceApplication.class, args);
	}

	@Autowired
	private BancoRepository bancoRepository;

	@Override
	public void run(String... args) {
		bancoRepository.findAll()
					.switchIfEmpty(save()).subscribe(e -> log.info("Save banco: {}", e.toString()));
	}
	private Flux<Banco> save() {
		return Flux.fromIterable(Arrays.asList(
				new Banco(null, "241", "Bradesco"),
				new Banco(null, "586", "Santander"),
				new Banco(null, "125", "Brasil")))
				.flatMap(b -> bancoRepository.save(b));
	}
}
