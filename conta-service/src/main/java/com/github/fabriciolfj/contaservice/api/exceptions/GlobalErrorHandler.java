package com.github.fabriciolfj.contaservice.api.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
@Order(-2)
@ControllerAdvice
@Log4j2
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

    public static final String UTF_8 = "UTF-8";

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

        log.error(throwable.getMessage(), throwable);

        var message = Objects.nonNull(throwable.getMessage()) ? throwable.getMessage() : "";
        var errorDetails = new ErrorDetails(System.currentTimeMillis(),
                message);

        var bufferFactory = serverWebExchange.getResponse().bufferFactory();
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        httpStatus = constraintViolation(throwable, errorDetails, httpStatus);
        httpStatus = domainBusinessException(throwable, errorDetails, httpStatus);

        serverWebExchange.getResponse().setStatusCode(httpStatus);
        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        var dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(errorDetails));
        return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    protected HttpStatus domainBusinessException(Throwable throwable, ErrorDetails errorDetails, HttpStatus httpStatus) {
        if (throwable instanceof DomainBusinessException) {
            errorDetails.setMessage(((DomainBusinessException) throwable).getReason());
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        } else if (throwable instanceof ResourceAccessException) {
            errorDetails.setMessage("Não foi possível consumir o recurso remoto.");
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        }
        return httpStatus;
    }

    protected HttpStatus constraintViolation(Throwable throwable, ErrorDetails errorDetails, HttpStatus httpStatus) {
        if (throwable instanceof WebExchangeBindException) {
            var webExchangeBindException = (WebExchangeBindException) throwable;
            if (Objects.nonNull(webExchangeBindException.getAllErrors())) {
                StringBuilder stringBuilder = new StringBuilder();
                webExchangeBindException.getAllErrors().forEach(objectError -> {
                    stringBuilder.append(objectError.getDefaultMessage() + "\n");
                });
                errorDetails.setMessage(stringBuilder.toString());
            }
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        }
        return httpStatus;
    }


}
