package com.github.fabriciolfj.contaservice.domain.entity;

import com.github.fabriciolfj.contaservice.api.exceptions.DomainBusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum TipoOperacao {

    SAQUE("saque"), OUTROS("outros"), ABERTURA("abertura"), CHEQUEESPECIAL("chequeespecial");

    private String descricao;

    public static TipoOperacao getOperacao(final String descricao) {
        return Stream.of(TipoOperacao.values())
                .filter(d -> d.descricao.equals(descricao))
                .findFirst()
                .orElseThrow(() -> new DomainBusinessException("Operação inexistente para a descrição: " + descricao));
    }
}
