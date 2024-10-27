package com.example.demo.mappers.mapStruct;
import com.example.demo.Entity.Personaje;
import com.example.demo.dto.PersonajeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonajeMapperStruct {

    PersonajeMapperStruct INSTANCE = Mappers.getMapper(PersonajeMapperStruct.class);

    PersonajeDto toDto(Personaje personaje);

    Personaje toEntity(PersonajeDto personajeDto);
}