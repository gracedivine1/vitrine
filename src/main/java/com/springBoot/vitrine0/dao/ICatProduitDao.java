/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.dao;

import com.springBoot.vitrine0.entities.CatProduit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DJEPANG
 */
public interface ICatProduitDao extends JpaRepository<CatProduit, Long>{
    
     public CatProduit findByNomCatProduit(String nomCatProduit);
    
}
