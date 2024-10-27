package com.example.demo.service;

import com.example.demo.dto.CapituloDto;
import com.example.demo.mappers.CapituloMapper;
import com.example.demo.repository.CapituloRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapituloService {

    private final CapituloRepository capituloRepository;

    @Autowired
    public CapituloService(CapituloRepository capituloRepository) {
        this.capituloRepository = capituloRepository;
    }


    public List<CapituloDto>getAllCap(){
        return capituloRepository.findAll().stream()
                .map(CapituloMapper::toDto)
                .collect(Collectors.toList());
    }


    public CapituloDto getCapituloById(int id) {
        return capituloRepository.findById(id)                                  // busco el capitulo
                .map(CapituloMapper::toDto)                                     // el capitulo encontrado lo convierto a Dto
                .orElseThrow(() -> new EntityNotFoundException("No encontrado Capitulo con ID: " + id));        // si no se ha encontrado capitulo lanzo excepcion
    }

    public CapituloDto createCap(CapituloDto capituloDto) {
        if (capituloDto == null) {
            throw new IllegalArgumentException("El Capitulo no puede ser nulo");
        }
        if(capituloRepository.existsById(capituloDto.getNumero_capitulo())){
            throw new IllegalArgumentException("Un Capitulo con esa ID ya existe");
        }
        return CapituloMapper.toDto(capituloRepository.save(CapituloMapper.toEntity(capituloDto)));
    }


    public CapituloDto updateCapitulo3(int id, CapituloDto capituloDto){
        if(capituloRepository.existsById(id)){
            capituloRepository.deleteById(id);
            capituloRepository.save(CapituloMapper.toEntity(capituloDto));
        }
        return CapituloMapper.toDto(capituloRepository.getReferenceById(id));
    }
    public void deleteCapitulo(int id) {
        if (!capituloRepository.existsById(id)) {
            throw new EntityNotFoundException("No encontrado Capitulo con ID: " + id);
        }
        capituloRepository.deleteById(id);
    }

}


//    public CapituloDto updateCapitulo(int id, CapituloDto capituloDto) {
//        return capituloRepository.findById(id)
//                .map(capituloEncontrado -> {
//                    capituloEncontrado.setNumero_capitulo(capituloDto.getNumero_capitulo());
//                    capituloEncontrado.setNombre_capitulo(capituloDto.getNombre_capitulo());
//
//                    //serie
//                    capituloEncontrado.getSerie().setId_serie(capituloDto.getSerieId());
//
//                    //personajes
//                    List<Personaje> personajes = capituloDto.getPersonajesId().stream()
//                            .map(ids -> {
//                                Personaje personaje = new Personaje();
//                                personaje.setId_personaje(ids);
//                                return personaje;
//                            })
//                            .collect(Collectors.toList());
//                    capituloEncontrado.setPersonajes(personajes);
//
//                    return CapituloMapper.toDto(capituloRepository.save(capituloEncontrado));
//                })
//                .orElseThrow(() -> new EntityNotFoundException("No encontrado Capitulo con ID: " + id));
//    }

//    //Capitulo update2 Solo se puede Actualziar el nombre del capitulo
//    public CapituloDto updateCapitulo2(int id, CapituloDto capituloDto){
//         var capituloActualziado = capituloRepository.findById(id)
//                .map(capituloEncontrado ->{
//                    capituloEncontrado.setNombre_capitulo(capituloDto.getNombre_capitulo());
//                    return  capituloEncontrado;
//                }).orElseThrow(()-> new IllegalArgumentException("No encontrado Capitulo con ID: " +id));
//                 return CapituloMapper.toDto(capituloRepository.save(capituloActualziado));
//    }