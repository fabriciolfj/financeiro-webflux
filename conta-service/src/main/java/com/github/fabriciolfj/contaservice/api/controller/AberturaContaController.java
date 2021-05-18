package com.github.fabriciolfj.contaservice.api.controller;

import com.github.fabriciolfj.contaservice.api.dto.request.ContaRequest;
import com.github.fabriciolfj.contaservice.api.dto.response.AberturaResponse;
import com.github.fabriciolfj.contaservice.domain.service.AberturaContaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/abertura")
@RequiredArgsConstructor
public class AberturaContaController {

    private final AberturaContaService aberturaContaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AberturaResponse> create(@RequestBody final ContaRequest request) {
        return aberturaContaService.execute(request);
    }

    /*public Mono<String> getData(String stockId) {
        return webClient.get()
                .uri(PATH_BY_ID, stockId)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new ServiceException("Server error", response.rawStatusCode())))
                .bodyToMono(String.class)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(5)) // 3 tentativas a cara 5 segundos
                        .filter(throwable -> throwable instanceof ServiceException) //caso de essa exceção, nao irá realizar a retentativa
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> { //trato a exceção, caso seja um serviceexception ou as retentativas nao derem certo
                            throw new ServiceException("External Service failed to process after max retries", HttpStatus.SERVICE_UNAVAILABLE.value());
                        }));
    }*/
}
