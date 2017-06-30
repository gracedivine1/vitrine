/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 *
 * @author DJEPANG
 */
@Entity
public class Panier {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private  int quantite;
    
    @Column
    private boolean prixUnitaire;
    
//    @ManyToOne(fetch = FetchType.LAZY) ....AVANT
    @ManyToOne
    @JoinColumn
    private Commande commande;
    
     @ManyToOne
    @JoinColumn
    private Produit produit;
    

    public Panier() {
    }

    public Panier(int quantite, boolean prixUnitaire, Commande commande, Produit produit) {
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.commande = commande;
        this.produit = produit;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public boolean isPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(boolean prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }





    
    
    
}
