/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.entities;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

/**
 *
 * @author DJEPANG
 */
@Entity
public class Compte implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private int typeCompte;
    
    @OneToOne
    @JoinColumn
    private  Personne personne;

    public Compte() {
    }

    public Compte(int typeCompte) {
        this.typeCompte = typeCompte;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(int typeCompte) {
        this.typeCompte = typeCompte;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
    
    
    
}
