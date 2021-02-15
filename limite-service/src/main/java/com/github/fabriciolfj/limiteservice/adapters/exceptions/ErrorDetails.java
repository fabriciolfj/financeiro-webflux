package com.github.fabriciolfj.limiteservice.adapters.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Setter
@ToString
public class ErrorDetails {

    private long timestamp;
    private String message;
}