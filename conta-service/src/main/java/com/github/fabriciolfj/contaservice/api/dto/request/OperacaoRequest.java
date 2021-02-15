package com.github.fabriciolfj.contaservice.api.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OperacaoRequest {

    private String tipo;
    private BigDecimal valor;
}
