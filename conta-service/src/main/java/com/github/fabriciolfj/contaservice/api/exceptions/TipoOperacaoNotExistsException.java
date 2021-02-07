package com.github.fabriciolfj.contaservice.api.exceptions;

public class TipoOperacaoNotExistsException extends RuntimeException {

    public TipoOperacaoNotExistsException(final String msg) {
        super(msg);
    }
}
