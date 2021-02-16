package com.github.fabriciolfj.limiteservice.adapters.in.criarlimite.routes.handler;

import com.github.fabriciolfj.limiteservice.adapters.in.criarlimite.routes.dto.request.LimiteRequest;
import com.github.fabriciolfj.limiteservice.adapters.in.criarlimite.mapper.LimiteMapper;
import com.github.fabriciolfj.limiteservice.application.domain.document.Limite;
import com.github.fabriciolfj.limiteservice.application.port.in.criarlimite.LimiteCreate;
import com.github.fabriciolfj.limiteservice.infra.commons.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LimiteHandler {

    private final LimiteCreate limiteCreate;
    private final LimiteMapper limiteMapper;

    public Mono<ServerResponse> post(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(LimiteRequest.class)
                .map(request -> limiteMapper.toDocument(request))
                .flatMap(limiteCreate::execute)
                .flatMap(doc -> {
                    var limite = (Limite) doc;
                    return ServerResponse.created(URI.create("/limites/" + limite.getId())).build();
                })
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(ErrorResponse.builder()
                        .dataHora(LocalDateTime.now())
                        .mensagem(e.getMessage())
                        .build()));

    }
}
