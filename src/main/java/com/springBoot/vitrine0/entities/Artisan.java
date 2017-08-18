/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.entities;


import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author DJEPANG
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Artisan extends Personne {

    @OneToMany(mappedBy = "artisan", cascade = {CascadeType.ALL})
    private Collection<Commande> commandes;

    @ManyToOne
    @JoinColumn
    private SousCatArt sousCatArt;

   

    public Artisan() {
        super();
    }

    public Artisan(SousCatArt sousCatArt, String nom, String prenom, String login, String motPasse, String email, String numTel, String ville,String sexe) {
        super(nom, prenom, login, motPasse, email, numTel, ville,sexe);
        this.sousCatArt = sousCatArt;
    }

    public Collection<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Collection<Commande> commandes) {
        this.commandes = commandes;
    }


    public SousCatArt getSousCatArt() {
        return sousCatArt;
    }

    public void setSousCatArt(SousCatArt sousCatArt) {
        this.sousCatArt = sousCatArt;
    }



}
