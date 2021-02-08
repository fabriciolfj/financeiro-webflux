package com.github.fabriciolfj.contaservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("extrato")
public class Extrato {

    @Id
    private String id;
    private String conta;
    private LocalDateTime data;
    private String operacao;
    private BigDecimal valor;
    private BigDecimal saldo;

    public void setOperacao(String operacao) {
        operacao = TipoOperacao.getOperacao(operacao)
                .getDescricao();
    }
}
