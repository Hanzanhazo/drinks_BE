package com.goormfj.hanzan.recipe.service;

import com.goormfj.hanzan.recipe.dto.AlcoholDTO;
import com.goormfj.hanzan.recipe.entity.Alcohol;
import com.goormfj.hanzan.recipe.exception.AlcoholNotFoundException;
import com.goormfj.hanzan.recipe.repository.AlcoholRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlcoholService {

    @Autowired
    private AlcoholRepository alcoholRepository;

    public Page<AlcoholDTO> getAlcoholsByType(String type, Pageable pageable) {
        return alcoholRepository.findByType(type, pageable)
                .map(this::convertToDTO);
    }

    public Page<AlcoholDTO> getAllAlcohols(Pageable pageable) {
        return alcoholRepository.findAll(pageable)
                                .map(this::convertToDTO);
    }

//    public AlcoholDTO getAlcoholDetails(Long id) {
//        Alcohol alcohol = alcoholRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Alcohol not found"));
//        return convertToDTO(alcohol);
//    }

    public AlcoholDTO getAlcoholDetails(Long id) {
        Alcohol alcohol = alcoholRepository.findById(id)
                .orElseThrow(() -> new AlcoholNotFoundException("Alcohol with ID " + id + " not found"));
        return convertToDTO(alcohol);
    }

    private AlcoholDTO convertToDTO(Alcohol alcohol) {

        AlcoholDTO dto = new AlcoholDTO();
        dto.setId(alcohol.getId());
        dto.setName(alcohol.getName());
        dto.setDescription(alcohol.getDescription());
        dto.setType(alcohol.getType());
        dto.setVolume(alcohol.getVolume());
        dto.setAlcoholContent(alcohol.getAlcoholContent());
        dto.setCountry(alcohol.getCountry());
        dto.setImageUrl(alcohol.getImageUrl());

        return dto;
    }

    public AlcoholDTO createAlcohol(AlcoholDTO alcoholDTO) {
        Alcohol alcohol = new Alcohol();
        alcohol.setName(alcoholDTO.getName());
        alcohol.setDescription(alcoholDTO.getDescription());
        alcohol.setType(alcoholDTO.getType());
        alcohol.setVolume(alcoholDTO.getVolume());
        alcohol.setAlcoholContent(alcoholDTO.getAlcoholContent());
        alcohol.setCountry(alcoholDTO.getCountry());
        alcohol.setImageUrl(alcoholDTO.getImageUrl());
        alcoholRepository.save(alcohol);

        return convertToDTO(alcohol);
    }
}
