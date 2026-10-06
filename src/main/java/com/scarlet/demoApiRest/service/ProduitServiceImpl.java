package com.scarlet.demoApiRest.service;

import com.scarlet.demoApiRest.dto.ProduitRequestDTO;
import com.scarlet.demoApiRest.dto.ProduitResponseDTO;
import com.scarlet.demoApiRest.entity.Produit;
import com.scarlet.demoApiRest.exception.ResourceNotFoundException;
import com.scarlet.demoApiRest.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService{

    private final ProduitRepository produitRepository;

    @Override
    public List<ProduitResponseDTO> lire() {
        return produitRepository.findAll()
                .stream()
                .map(this::enDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProduitResponseDTO creer(ProduitRequestDTO requestDTO) {

        Produit produitASave = enEntity(requestDTO);

        Produit produitSave = produitRepository.save(produitASave);

        return enDTO(produitSave);
    }

    @Override
    public ProduitResponseDTO modifier(Long id, ProduitRequestDTO requestDTO) {

        return produitRepository.findById(id)
                .map(produitExistant ->{
                    produitExistant.setNom(requestDTO.getNom());
                    produitExistant.setDescription(requestDTO.getDescription());
                    produitExistant.setPrix(requestDTO.getPrix());

                    Produit produitMAJ = produitRepository.save(produitExistant);
                    return enDTO(produitMAJ);

                }).orElseThrow(() -> new ResourceNotFoundException("Pas de produit avec l'id :" + id));
    }

    @Override
    public String supprimer(Long id) {

        Produit existProduct = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun produit ne possède l'id: " + id));

        produitRepository.delete(existProduct);

        return "Le produit avec l'id = " + id + " a été suppimer.";
    }


    //CONVENTIONS... (MAPPING)

    //DTO en Entité...
    private Produit enEntity(ProduitRequestDTO requestDTO){

        Produit produit = new Produit();

        produit.setNom(requestDTO.getNom());
        produit.setDescription(requestDTO.getDescription());
        produit.setPrix(requestDTO.getPrix());

        return produit;
    }

    //Entity en DTO
    private ProduitResponseDTO enDTO(Produit produit){

        ProduitResponseDTO responseDTO = new ProduitResponseDTO();

        responseDTO.setId(produit.getId());
        responseDTO.setNom(produit.getNom());
        responseDTO.setDescription(produit.getDescription());
        responseDTO.setPrix(produit.getPrix());

        return responseDTO;
    }

}
