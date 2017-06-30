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
import javax.persistence.Table;

/**
 *
 * @author DJEPANG
 */
@Entity
public class CatArt implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String nomCatArt;
    
//    @OneToMany(mappedBy = "catArt", fetch = FetchType.LAZY)
//    private Collection<SousCatArt> sousCatArts;
    
    public CatArt() {
    }

    public CatArt(String nomCatArt) {
        this.nomCatArt = nomCatArt;
    }

//    public CatArt(String nomCathArt,Collection<SousCatArt> sousCatArts){ 
//        this.nomCatArt = nomCatArt;
//        this.sousCatArts = sousCatArts;
//    }
//    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCatArt() {
        return nomCatArt;
    }

    public void setNomCatArt(String nomCatArt) {
        this.nomCatArt = nomCatArt;
    }
//
//    public Collection<SousCatArt> getSousCatArts() {
//        return sousCatArts;
//    }
//
//    public void setSousCatArts(Collection<SousCatArt> sousCatArts) {
//        this.sousCatArts = sousCatArts;
//    }

    
    
    
}
