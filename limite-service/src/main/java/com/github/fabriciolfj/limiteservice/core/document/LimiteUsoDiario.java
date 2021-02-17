package com.github.fabriciolfj.limiteservice.core.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "limiteUsoDiario")
public class LimiteUsoDiario {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String limite;
    private String transacao;
    private BigDecimal valor;
    private LocalDate data;
}
