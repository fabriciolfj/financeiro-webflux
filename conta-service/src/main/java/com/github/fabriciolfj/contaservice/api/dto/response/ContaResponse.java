package com.github.fabriciolfj.contaservice.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaResponse {

    private String numero;
    private String banco;
}
