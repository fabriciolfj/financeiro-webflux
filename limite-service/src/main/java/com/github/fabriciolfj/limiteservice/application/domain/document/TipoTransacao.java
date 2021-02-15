package com.github.fabriciolfj.limiteservice.application.domain.document;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum TipoTransacao {

    SAQUE("saque"), OUTROS("outros");

    private String descricao;

    public static String toEnum(final String value) {
        return Stream.of(TipoTransacao.values())
                .filter(d -> d.descricao.equalsIgnoreCase(value))
                .map(v ->  v.getDescricao())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo transação não localizada para o valor: " + value));
    }
}
