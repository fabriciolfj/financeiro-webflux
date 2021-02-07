package com.github.fabriciolfj.contaservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table("extrato")
public class Extrato {

    @Id
    @Column("id")
    private Long id;
    @Column("conta_id")
    private Long conta;
    private LocalDateTime data;
    private String operacao;
    private BigDecimal valor;
    private BigDecimal saldo;

    public void setOperacao(String operacao) {
        operacao = TipoOperacao.getOperacao(operacao)
                .getDescricao();
    }
}
