package com.scarlet.demoApiRest.service;

import com.scarlet.demoApiRest.dto.ProduitRequestDTO;
import com.scarlet.demoApiRest.dto.ProduitResponseDTO;
import com.scarlet.demoApiRest.entity.Produit;
import com.scarlet.demoApiRest.exception.ResourceNotFoundException;
import com.scarlet.demoApiRest.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService{

    private final ProduitRepository produitRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProduitResponseDTO> lire() {
        return produitRepository.findAll()
                .stream()
                .map(this::enDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProduitResponseDTO trouver(Long id) {
        return produitRepository.findById(id)
                .map(this::enDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun produit ne possède l'id: " + id));
    }

    @Override
    @Transactional
    public ProduitResponseDTO creer(ProduitRequestDTO requestDTO) {

        Produit produitASave = enEntity(requestDTO);

        Produit produitSave = produitRepository.save(produitASave);

        return enDTO(produitSave);
    }

    @Override
    @Transactional
    public ProduitResponseDTO modifier(Long id, ProduitRequestDTO requestDTO) {

        return produitRepository.findById(id)
                .map(produitExistant ->{
                    produitExistant.setNom(requestDTO.getNom());
                    produitExistant.setDescription(requestDTO.getDescription());
                    produitExistant.setPrix(requestDTO.getPrix());

                    Produit produitMAJ = produitRepository.save(produitExistant);
                    return enDTO(produitMAJ);

                }).orElseThrow(() -> new ResourceNotFoundException("Aucun produit ne possède l'id: " + id));
    }

    @Override
    @Transactional
    public void supprimer(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new ResourceNotFoundException("Aucun produit ne possède l'id: " + id);
        }
        produitRepository.deleteById(id);
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
