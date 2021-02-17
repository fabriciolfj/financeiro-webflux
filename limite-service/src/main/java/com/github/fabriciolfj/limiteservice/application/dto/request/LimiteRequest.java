package com.github.fabriciolfj.limiteservice.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LimiteRequest {

    private String conta;
    @JsonProperty("saque")
    private Integer volumeSaque;
    @JsonProperty("valor")
    private BigDecimal valorDiario;
}
