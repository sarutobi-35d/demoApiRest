package com.scarlet.demoApiRest.service;

import com.scarlet.demoApiRest.entity.Produit;
import com.scarlet.demoApiRest.repository.ProduitRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
//@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService{

    private final ProduitRepository produitRepository;

    @Override
    public Produit creer(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public List<Produit> lire() {
        return produitRepository.findAll();
    }

    @Override
    public Produit modifier(Long id, Produit produit) {
        return produitRepository.findById(id)
                .map(p -> {
                    p.setPrix(produit.getPrix());
                    p.setNom(produit.getNom());
                    p.setDescription(produit.getDescription());
                    return produitRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Produit non trouvé !"));
    }

    @Override
    public String supprimer(Long id) {
        produitRepository.deleteById(id);
        return "Produit supprimé !";
    }
}
