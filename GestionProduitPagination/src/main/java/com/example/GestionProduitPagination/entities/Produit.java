package com.example.GestionProduitPagination.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Produit {
    @Id
    @GeneratedValue
    private Long id;
    //cad que j'accepte pas que ce champs soit NUll
    @NotNull(message = "La désignation est obligatoire")
    @Size(min=3, max=15, message = "La désignation doit avoir entre 3 et 15 caractères")
    private String desigantion;

    @DecimalMin(value = "100", message = "Le prix doit être d'au moins 100")
    private double prix;

    private int quantite;

    public Produit() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Produit(String desigantion, double prix, int quantite) {
        super();
        this.desigantion = desigantion;
        this.prix = prix;
        this.quantite = quantite;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDesigantion() {
        return desigantion;
    }
    public void setDesigantion(String desigantion) {
        this.desigantion = desigantion;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}
