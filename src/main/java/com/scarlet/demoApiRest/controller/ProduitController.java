package com.scarlet.demoApiRest.controller;

import com.scarlet.demoApiRest.dto.ProduitRequestDTO;
import com.scarlet.demoApiRest.dto.ProduitResponseDTO;
import com.scarlet.demoApiRest.service.ProduitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/produit")
@AllArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @GetMapping("/read")
    public List<ProduitResponseDTO> read(){
        return produitService.lire();
    }

    @PostMapping("/create")
    public ProduitResponseDTO create(@RequestBody ProduitRequestDTO requestDTO){
        return produitService.creer(requestDTO);
    }


    @PutMapping("/update/{id}")
    public ProduitResponseDTO update(@PathVariable Long id, @RequestBody ProduitRequestDTO requestDTO){
        return produitService.modifier(id, requestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return produitService.supprimer(id);
    }

}
