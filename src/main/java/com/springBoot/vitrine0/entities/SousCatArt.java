/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
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
public class SousCatArt implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String nomSousCatArt;
    
    @ManyToOne
    @JoinColumn
    private CatArt catArt;
    

//    @OneToMany(mappedBy = "sousCatArt", fetch = FetchType.LAZY)
//    private Collection<Artisan> artisans;

    public SousCatArt() {
    }

    public SousCatArt(String nomSousCatArt) {
        this.nomSousCatArt = nomSousCatArt;
    }

    public SousCatArt( String nomSousCatArt, CatArt catArt) {
        
        this.nomSousCatArt = nomSousCatArt;
        this.catArt = catArt;
    }
    
    

//    public SousCatArt(String nomSousCatArt, CatArt catArt, Collection<Artisan> artisans) {
//        this.nomSousCatArt = nomSousCatArt;
//        this.catArt = catArt;
//        this.artisans = artisans;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomSousCatArt() {
        return nomSousCatArt;
    }

    public void setNomSousCatArt(String nomSousCatArt) {
        this.nomSousCatArt = nomSousCatArt;
    }

//    public Collection<Artisan> getArtisans() {
//        return artisans;
//    }
//
//    public void setArtisans(Collection<Artisan> artisans) {
//        this.artisans = artisans;
//    }

    public CatArt getCatArt() {
        return catArt;
    }

    public void setCatArt(CatArt catArt) {
        this.catArt = catArt;
    }
//
   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nomSousCatArt);
        hash = 37 * hash + Objects.hashCode(this.catArt);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SousCatArt other = (SousCatArt) obj;
        if (!Objects.equals(this.nomSousCatArt, other.nomSousCatArt)) {
            return false;
        }
        if (!Objects.equals(this.catArt, other.catArt)) {
            return false;
        }
        return true;
    }


}
