package com.github.fabriciolfj.limiteservice.adapters.in.mensageria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AtualizarContaDTO {

    private String conta;
    private Integer qtdSaqueMensal;
    private BigDecimal valorDiario;
    private String data;
}
