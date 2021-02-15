package com.github.fabriciolfj.limiteservice.adapters.routes.limite.route;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import com.github.fabriciolfj.limiteservice.adapters.routes.limite.handler.LimiteHandler;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.method;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@RequiredArgsConstructor
public class LimiteRoute {

    private final LimiteHandler limiteHandler;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return nest(path("/limites"), nest(accept(APPLICATION_JSON),
                route(method(HttpMethod.POST), limiteHandler::post)));
    }
}
