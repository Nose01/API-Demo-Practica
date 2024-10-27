package com.example.demo.mappers.mapStruct;
import com.example.demo.Entity.Serie;
import com.example.demo.dto.SerieDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface SerieMapperStruct {

    SerieMapperStruct INSTANCE = Mappers.getMapper(SerieMapperStruct.class);

    @Mapping(target = "capitulosIds", source = "capitulos.numero_capitulo")
    SerieDto toDto(Serie serie);

    @Mapping(target = "capitulos", ignore = true)
    Serie toEntity(SerieDto serieDto);
}