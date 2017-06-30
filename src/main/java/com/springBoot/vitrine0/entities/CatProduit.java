/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author DJEPANG
 */
@Entity
@Table(name = "CatProduit")
public class CatProduit implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String nomCatProduit;
    
    @JsonIgnore
    @OneToMany(mappedBy = "catProduit", fetch = FetchType.LAZY)
    private Collection<Produit> produits;
    //Constructeurs

    public CatProduit() {
    }

    public CatProduit(String nomCatProduit, Collection<Produit> produits) {
        this.nomCatProduit = nomCatProduit;
        this.produits = produits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCatProduit() {
        return nomCatProduit;
    }

    public void setNomCatProduit(String nomCatProduit) {
        this.nomCatProduit = nomCatProduit;
    }

    public Collection<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }



 

}
