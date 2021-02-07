package com.github.fabriciolfj.contaservice.api.dto.mapper;

import com.github.fabriciolfj.contaservice.api.dto.request.ContaRequest;
import com.github.fabriciolfj.contaservice.api.dto.response.ContaResponse;
import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContaMapper {

    @Mapping(source = "numero", target = "numero")
    @Mapping(target = "banco", ignore = true)
    ContaResponse toResponse(final Conta conta);

    @Mapping(source = "numero", target = "numero")
    @Mapping(source = "cliente", target = "cliente")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "banco", ignore = true)
    Conta toEntity(final ContaRequest request);
}
