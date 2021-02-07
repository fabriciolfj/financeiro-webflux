package com.github.fabriciolfj.contaservice.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaRequest {

    private String numero;
    private String cliente;
    private BigDecimal saldo;
    @JsonProperty("code_banco")
    private String codeBanco;
}
