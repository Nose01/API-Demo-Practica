package com.example.demo.controller;

import com.example.demo.dto.SerieDto;
import com.example.demo.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SerieController {
    private final com.example.demo.service.serviceMapStruct.SerieService serieRepository;

    @Autowired
    public SerieController(SerieService serieRepository, com.example.demo.service.serviceMapStruct.SerieService serieRepository1) {

        this.serieRepository = serieRepository1;
    }



    @GetMapping("/series")
    public ResponseEntity<List<SerieDto>> getAllSeries(){

        return ResponseEntity.ok(serieRepository.getAllSerie());
    }

    @GetMapping("/series/{id}")
    public ResponseEntity<SerieDto> getSerieById(@PathVariable int id){
        return ResponseEntity.ok(serieRepository.getSerieById(id));
    }

    @PostMapping("/series")
    public ResponseEntity<SerieDto> createSerie(@RequestBody SerieDto serieDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(serieRepository.createSerie(serieDto));
    }

    // con mi mapper seria updateSerie3
    @PutMapping("/series/{id}")
    public ResponseEntity<SerieDto> updateSeries(@PathVariable int id, @RequestBody SerieDto serieDto){
        return ResponseEntity.ok(serieRepository.updateSerie(id,serieDto));
    }

    @DeleteMapping("/series/{id}")
    public ResponseEntity<Void> deleteSerie(@PathVariable int id){
        serieRepository.deleteSerieById(id);
        return ResponseEntity.noContent().build();
    }

}
