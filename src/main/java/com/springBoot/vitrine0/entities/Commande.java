/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DJEPANG
 */
@Entity
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Personne personne; 
    
    @Column
    private int statut;
   
    @Column
    @Temporal(TemporalType.DATE)
    private Date date;


    @ManyToOne
    @JoinColumn
    private Artisan artisan;
    
    @JsonIgnore
    @OneToMany(mappedBy = "commande", fetch = FetchType.LAZY)
    private Collection<Panier> paniers;
        
    

    public Commande() {
    }

    public Commande(Personne personne, int statut, Date date, Artisan artisan) {
        this.personne = personne;
        this.statut = statut;
        this.date = date;
        this.artisan = artisan;
    }

    
//    public Commande(int statut, Date date, Personne personne, Artisan artisan, Collection<Panier> paniers) {
//        this.statut = statut;
//        this.date = date;
//        this.personne = personne;
//        this.artisan = artisan;
//        this.paniers = paniers;
//    }

    public Artisan getArtisan() {
        return artisan;
    }

    public void setArtisan(Artisan artisan) {
        this.artisan = artisan;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Collection<Panier> getPaniers() {
        return paniers;
    }

    public void setPaniers(Collection<Panier> paniers) {
        this.paniers = paniers;
    }



}
