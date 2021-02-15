package com.github.fabriciolfj.limiteservice.adapters.routes.limite.handler;

import com.github.fabriciolfj.limiteservice.adapters.routes.limite.dto.request.LimiteRequest;
import com.github.fabriciolfj.limiteservice.adapters.routes.limite.mapper.LimiteMapper;
import com.github.fabriciolfj.limiteservice.application.port.in.criarlimite.LimiteCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class LimiteHandler {

    private final LimiteCreate limiteCreate;
    private final LimiteMapper limiteMapper;

    public Mono<ServerResponse> post(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(LimiteRequest.class)
                .map(request -> limiteMapper.toDocument(request))
                .flatMap(limiteCreate::execute)
                .flatMap(doc -> ServerResponse.created(URI.create("/limites/" + doc.getId())).build());

    }
}
