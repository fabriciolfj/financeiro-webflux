package com.github.fabriciolfj.contaservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("conta")
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String numero;
    private String banco;
    private String cliente;
}
