package com.github.fabriciolfj.limiteservice.core.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "limites")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Limite {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String contaComDigito;
    private BigDecimal valorDiario;
    private Integer quantidadeSaqueMensal;
}
