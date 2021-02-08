package com.github.fabriciolfj.contaservice.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LimiteRequest {

    private Integer saque;
    private BigDecimal valor;
}
