/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.dao;

import com.springBoot.vitrine0.entities.CatArt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DJEPANG
 */
public interface ICatArtDao extends JpaRepository<CatArt, Long>{
    
    public CatArt findByNomCatArt(String nomCatArt);
    
}
