package com.example.GestionProduitPagination.dao;

import com.example.GestionProduitPagination.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    @Query("select p from Produit p where p.desigantion like :x")
    public Page<Produit> Chercher(@Param("x")String mc, org.springframework.data.domain.Pageable pageable);
}
