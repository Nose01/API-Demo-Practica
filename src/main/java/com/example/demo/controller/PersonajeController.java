package com.example.demo.controller;

import com.example.demo.dto.PersonajeDto;
import com.example.demo.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonajeController {
    private final com.example.demo.service.serviceMapStruct.PersonajeService personajeService;

    @Autowired
    public PersonajeController(PersonajeService personajeService, com.example.demo.service.serviceMapStruct.PersonajeService personajeService1) {

        this.personajeService = personajeService1;
    }


    @GetMapping("/personajes")
    public ResponseEntity<List<PersonajeDto>> getAllPersonajes(){
        return ResponseEntity.ok(personajeService.getAllPer());
    }

    @GetMapping("/personajes/{id}")
    public ResponseEntity<PersonajeDto> getPersonajeById(@PathVariable int id){
        return ResponseEntity.ok(personajeService.getPersonajeById(id));
    }

    @PostMapping("/personajes")
    public ResponseEntity<PersonajeDto> createPersonaje(@RequestBody PersonajeDto personajeDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeService.createPer(personajeDto));
    }

    // con mi mapper seria updatePersonaje3
    @PutMapping("/personajes/{id}")
    public ResponseEntity<PersonajeDto> update(@PathVariable int id, @RequestBody PersonajeDto personajeDto){
        PersonajeDto updatedPersonaje = personajeService.updatePersonaje(id,personajeDto);
        return ResponseEntity.ok(updatedPersonaje);
    }

    @DeleteMapping("/personajes/{id}")
    public ResponseEntity<Void> deletePersonaje(@PathVariable int id){
        personajeService.deletePerById(id);
        return ResponseEntity.noContent().build();
    }
}
