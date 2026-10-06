package com.scarlet.demoApiRest.controller;

import com.scarlet.demoApiRest.dto.ProduitRequestDTO;
import com.scarlet.demoApiRest.dto.ProduitResponseDTO;
import com.scarlet.demoApiRest.service.ProduitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @GetMapping
    public List<ProduitResponseDTO> read(){
        return produitService.lire();
    }

    @GetMapping("/{id}")
    public ProduitResponseDTO trouver(@PathVariable Long id) {
        return produitService.trouver(id);
    }

    @PostMapping
    public ProduitResponseDTO create(@Valid @RequestBody ProduitRequestDTO requestDTO){
        return produitService.creer(requestDTO);
    }


    @PutMapping("/{id}")
    public ProduitResponseDTO update(@PathVariable Long id, @Valid @RequestBody ProduitRequestDTO requestDTO){
        return produitService.modifier(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        produitService.supprimer(id);
    }

}
