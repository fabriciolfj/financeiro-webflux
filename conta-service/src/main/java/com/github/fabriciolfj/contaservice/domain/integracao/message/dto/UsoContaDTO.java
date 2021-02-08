package com.github.fabriciolfj.contaservice.domain.integracao.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsoContaDTO {

    private String conta;
    private String tipo;
    private BigDecimal valor;
    private String data;
}
