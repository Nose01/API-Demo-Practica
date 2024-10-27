package com.example.demo.mappers.mapStruct;

import com.example.demo.Entity.Capitulo;
import com.example.demo.dto.CapituloDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CapituloMapperStruct {

    CapituloMapperStruct INSTANCE = Mappers.getMapper(CapituloMapperStruct.class);

    @Mapping(target = "serieId", source = "serie.id_serie")
    CapituloDto toDto(Capitulo capitulo);

    @Mapping(target = "serie.id_serie", source = "serieId")
    Capitulo toEntity(CapituloDto capituloDto);
}
