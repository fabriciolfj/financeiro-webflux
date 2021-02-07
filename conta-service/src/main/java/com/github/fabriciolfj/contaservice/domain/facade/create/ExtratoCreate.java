package com.github.fabriciolfj.contaservice.domain.facade.create;

import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import com.github.fabriciolfj.contaservice.domain.entity.Extrato;
import com.github.fabriciolfj.contaservice.domain.entity.TipoOperacao;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class ExtratoCreate {

    public Extrato abertura(final Conta conta, final BigDecimal valor) {
        return new Extrato(null, conta.getId(), LocalDateTime.now(), TipoOperacao.ABERTURA.getDescricao(), valor, valor);
    }
}
