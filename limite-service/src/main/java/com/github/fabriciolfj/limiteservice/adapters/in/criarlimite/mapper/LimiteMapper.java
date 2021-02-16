package com.github.fabriciolfj.limiteservice.adapters.in.criarlimite.mapper;

import com.github.fabriciolfj.limiteservice.adapters.in.criarlimite.integracao.dto.AtualizarContaDTO;
import com.github.fabriciolfj.limiteservice.adapters.in.criarlimite.routes.dto.request.LimiteRequest;
import com.github.fabriciolfj.limiteservice.application.domain.document.Limite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LimiteMapper {


    @Mapping(source = "conta", target = "contaComDigito")
    @Mapping(source = "valorDiario", target = "valorDiario")
    @Mapping(source = "volumeSaque", target = "quantidadeSaqueMensal")
    Limite toDocument(final LimiteRequest request);

    @Mapping(source = "conta", target = "contaComDigito")
    @Mapping(source = "valorDiario", target = "valorDiario")
    @Mapping(source = "qtdSaqueMensal", target = "quantidadeSaqueMensal")
    Limite toDocument(final AtualizarContaDTO dto);
}
