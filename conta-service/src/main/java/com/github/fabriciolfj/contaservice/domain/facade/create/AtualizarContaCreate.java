package com.github.fabriciolfj.contaservice.domain.facade.create;

import com.github.fabriciolfj.contaservice.api.dto.request.LimiteRequest;
import com.github.fabriciolfj.contaservice.domain.integracao.message.dto.AtualizarContaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class AtualizarContaCreate {

    public Mono<AtualizarContaDTO> execute(final LimiteRequest limiteRequest, final String conta) {
        var dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return Mono.just(new AtualizarContaDTO(conta, limiteRequest.getSaque(), limiteRequest.getValor(), dateFormat.format(LocalDate.now())));
    }
}
