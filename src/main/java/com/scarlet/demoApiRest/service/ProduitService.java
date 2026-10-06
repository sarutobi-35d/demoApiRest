package com.scarlet.demoApiRest.service;

import com.scarlet.demoApiRest.dto.ProduitRequestDTO;
import com.scarlet.demoApiRest.dto.ProduitResponseDTO;
import com.scarlet.demoApiRest.entity.Produit;

import java.util.List;

public interface ProduitService {

    List<ProduitResponseDTO> lire();

    ProduitResponseDTO creer(ProduitRequestDTO requestDTO);

    ProduitResponseDTO modifier(Long id, ProduitRequestDTO requestDTO);

   String supprimer(Long id);
}
