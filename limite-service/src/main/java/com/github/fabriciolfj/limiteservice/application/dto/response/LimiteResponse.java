package com.github.fabriciolfj.limiteservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LimiteResponse {

    private Integer saque;
    private BigDecimal valor;
}