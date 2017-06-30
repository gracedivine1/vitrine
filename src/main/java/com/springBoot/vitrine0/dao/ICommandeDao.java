/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.dao;

import  com.springBoot.vitrine0.entities.Commande;
import java.util.Collection;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DJEPANG
 */
public interface ICommandeDao extends JpaRepository<Commande, Long>{
    
     public Collection<Commande> findCommandeByDate(Date date);
    
}
