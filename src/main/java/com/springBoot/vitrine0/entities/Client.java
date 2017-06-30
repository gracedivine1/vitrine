/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.entities;


import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author DJEPANG
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Client extends Personne{

    public Client() {
        super();
    }

    public Client(String nom, String prenom, String login, String motPasse, String email, String numTel, String ville) {
        super(nom, prenom, login, motPasse, email, numTel, ville);
    }

  
    
}
