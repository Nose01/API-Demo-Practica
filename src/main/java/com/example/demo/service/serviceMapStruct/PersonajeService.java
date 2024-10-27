package com.example.demo.service.serviceMapStruct;

import com.example.demo.Entity.Personaje;
import com.example.demo.dto.PersonajeDto;
import com.example.demo.mappers.mapStruct.PersonajeMapperStruct;
import com.example.demo.repository.PersonajeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonajeService {

    private final PersonajeRepository personajeRepository;
    private final PersonajeMapperStruct personajeMapperStruct;

    @Autowired
    public PersonajeService(PersonajeRepository personajeRepository, PersonajeMapperStruct personajeMapperStruct) {
        this.personajeRepository = personajeRepository;
        this.personajeMapperStruct = personajeMapperStruct;
    }

    public List<PersonajeDto> getAllPer() {
        return personajeRepository.findAll().stream()
                .map(personajeMapperStruct::toDto)
                .collect(Collectors.toList());
    }

    public PersonajeDto getPersonajeById(int id) {
        return personajeRepository.findById(id)
                .map(personajeMapperStruct::toDto)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado Personaje con ID: " + id));
    }

    public PersonajeDto createPer(PersonajeDto personajeDto) {
        return personajeMapperStruct.toDto(
                personajeRepository.save(personajeMapperStruct.toEntity(personajeDto))
        );
    }

    public PersonajeDto updatePersonaje(int id, PersonajeDto personajeDto) {
        if (!personajeRepository.existsById(id)) {
            throw new EntityNotFoundException("No encontrado Personaje con ID: " + id);
        }
        Personaje updatedPersonaje = personajeMapperStruct.toEntity(personajeDto);
        updatedPersonaje.setId_personaje(id);
        return personajeMapperStruct.toDto(personajeRepository.save(updatedPersonaje));
    }

    public void deletePerById(int id) {
        if (!personajeRepository.existsById(id)) {
            throw new EntityNotFoundException("No encontrado Personaje con ID: " + id);
        }
        personajeRepository.deleteById(id);
    }
}