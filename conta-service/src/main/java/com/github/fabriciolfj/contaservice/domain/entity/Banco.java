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
@NoArgsConstructor
@AllArgsConstructor
@Table("banco")
public class Banco {

    @Id
    @Column("id")
    @EqualsAndHashCode.Include
    private Long id;
    private String code;
    private String descricao;
}
