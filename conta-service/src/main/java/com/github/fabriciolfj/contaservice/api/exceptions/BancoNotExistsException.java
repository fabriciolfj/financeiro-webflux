package com.github.fabriciolfj.contaservice.api.exceptions;

public class BancoNotExistsException extends RuntimeException {

    public BancoNotExistsException(final String msg) {
        super(msg);
    }
}
