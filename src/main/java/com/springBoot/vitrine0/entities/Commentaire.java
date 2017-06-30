/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.entities;


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


/**
 *
 * @author DJEPANG
 */
@Entity
public class Commentaire implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String commCom;
    
    @ManyToOne
    @JoinColumn
    private Produit produit;
    
        
    @ManyToOne
    @JoinColumn
    private Personne personne;

    public Commentaire() {
    }

    public Commentaire(String commCom, Produit produit) {
        this.commCom = commCom;
        this.produit = produit;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComm_com() {
        return commCom;
    }

    public void setComm_com(String commCom) {
        this.commCom = commCom;
    }

    public String getCommCom() {
        return commCom;
    }

    public void setCommCom(String commCom) {
        this.commCom = commCom;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }


    
    
    
}
