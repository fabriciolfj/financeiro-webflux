package com.github.fabriciolfj.limiteservice.adapters.routes.limite.mapper;

import com.github.fabriciolfj.limiteservice.adapters.routes.limite.dto.request.LimiteRequest;
import com.github.fabriciolfj.limiteservice.application.domain.document.Limite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LimiteMapper {


    @Mapping(source = "conta", target = "contaComDigito")
    @Mapping(source = "valorDiario", target = "valorDiario")
    @Mapping(source = "volumeSaque", target = "quantidadeSaqueMensal")
    Limite toDocument(final LimiteRequest request);
}
