package com.scarlet.demoApiRest.repository;

import com.scarlet.demoApiRest.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
