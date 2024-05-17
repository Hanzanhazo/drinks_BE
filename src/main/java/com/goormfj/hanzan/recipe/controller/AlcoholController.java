package com.goormfj.hanzan.recipe.controller;


import com.goormfj.hanzan.recipe.dto.AlcoholDTO;
import com.goormfj.hanzan.recipe.service.AlcoholService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/alcohols")
public class AlcoholController {

    @Autowired
    private  AlcoholService alcoholService;

    @GetMapping
    public ResponseEntity<Page<AlcoholDTO>> getAlcohols(@RequestParam(required = false) String type, Pageable pageable) {
        if (type == null || type.isEmpty()) {
            return ResponseEntity.ok(alcoholService.getAllAlcohols(pageable));
        }
        return ResponseEntity.ok(alcoholService.getAlcoholsByType(type, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlcoholDTO> getAlcoholDetails(@PathVariable Long id) {
        return ResponseEntity.ok(alcoholService.getAlcoholDetails(id));
    }

    @PostMapping
    public ResponseEntity<AlcoholDTO> createAlcohol(@RequestBody AlcoholDTO alcoholDTO) {
        AlcoholDTO createdAlcohol = alcoholService.createAlcohol(alcoholDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlcohol);
    }
}
