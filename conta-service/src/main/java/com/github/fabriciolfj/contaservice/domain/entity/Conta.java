package com.github.fabriciolfj.contaservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table("conta")
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    @Id
    @EqualsAndHashCode.Include
    @Column("id")
    private Long id;
    private String numero;
    @Column("banco_id")
    private Long banco;
    private String cliente;
}
