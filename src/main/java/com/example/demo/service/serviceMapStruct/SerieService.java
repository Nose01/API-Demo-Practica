package com.example.demo.service.serviceMapStruct;

import com.example.demo.Entity.Serie;
import com.example.demo.dto.SerieDto;
import com.example.demo.mappers.mapStruct.SerieMapperStruct;
import com.example.demo.repository.SerieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    private final SerieRepository serieRepository;
    private final SerieMapperStruct serieMapperStruct;

    @Autowired
    public SerieService(SerieRepository serieRepository, SerieMapperStruct serieMapperStruct) {
        this.serieRepository = serieRepository;
        this.serieMapperStruct = serieMapperStruct;
    }

    public List<SerieDto> getAllSerie() {
        return serieRepository.findAll().stream()
                .map(serieMapperStruct::toDto)
                .collect(Collectors.toList());
    }

    public SerieDto getSerieById(int id) {
        return serieRepository.findById(id)
                .map(serieMapperStruct::toDto)
                .orElseThrow(() -> new EntityNotFoundException("No encontrado Serie con ID: " + id));
    }

    public SerieDto createSerie(SerieDto serieDto) {
        return serieMapperStruct.toDto(
                serieRepository.save(serieMapperStruct.toEntity(serieDto))
        );
    }

    public SerieDto updateSerie(int id, SerieDto serieDto) {
        if (!serieRepository.existsById(id)) {
            throw new EntityNotFoundException("No encontrada Serie con ID: " + id);
        }
        Serie updatedSerie = serieMapperStruct.toEntity(serieDto);
        updatedSerie.setId_serie(id);
        return serieMapperStruct.toDto(serieRepository.save(updatedSerie));
    }

    public void deleteSerieById(int id) {
        if (!serieRepository.existsById(id)) {
            throw new EntityNotFoundException("No encontrada Serie con ID: " + id);
        }
        serieRepository.deleteById(id);
    }
}