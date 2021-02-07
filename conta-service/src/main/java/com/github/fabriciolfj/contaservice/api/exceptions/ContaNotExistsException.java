package com.github.fabriciolfj.contaservice.api.exceptions;

public class ContaNotExistsException extends RuntimeException {

    public ContaNotExistsException(final String msg) {
        super(msg);
    }
}
