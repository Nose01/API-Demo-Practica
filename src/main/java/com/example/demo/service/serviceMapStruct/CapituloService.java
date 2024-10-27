package com.example.demo.service.serviceMapStruct;
import com.example.demo.Entity.Capitulo;
import com.example.demo.dto.CapituloDto;
import com.example.demo.mappers.mapStruct.CapituloMapperStruct;
import com.example.demo.repository.CapituloRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapituloService {

    private final CapituloRepository capituloRepository;
    private final CapituloMapperStruct capituloMapperStruct;

    @Autowired
    public CapituloService(CapituloRepository capituloRepository, CapituloMapperStruct capituloMapperStruct) {
        this.capituloRepository = capituloRepository;
        this.capituloMapperStruct = capituloMapperStruct;
    }

    public List<CapituloDto> getAllCap() {
        return capituloRepository.findAll().stream()
                .map(capituloMapperStruct::toDto)
                .collect(Collectors.toList());
    }

    public CapituloDto getCapituloById(int id) {
        return capituloRepository.findById(id)
                .map(capituloMapperStruct::toDto)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado Capitulo con ID: " + id));
    }

    public CapituloDto createCap(CapituloDto capituloDto) {
        return capituloMapperStruct.toDto(
                capituloRepository.save(capituloMapperStruct.toEntity(capituloDto))
        );
    }

    public CapituloDto updateCapitulo(int id, CapituloDto capituloDto) {
        if (!capituloRepository.existsById(id)) {
            throw new EntityNotFoundException("No encontrado Capitulo con ID: " + id);
        }
        Capitulo updatedCapitulo = capituloMapperStruct.toEntity(capituloDto);
        updatedCapitulo.setNumero_capitulo(id);
        return capituloMapperStruct.toDto(capituloRepository.save(updatedCapitulo));
    }

    public void deleteCapitulo(int id) {
        if (!capituloRepository.existsById(id)) {
            throw new EntityNotFoundException("No encontrado Capitulo con ID: " + id);
        }
        capituloRepository.deleteById(id);
    }
}