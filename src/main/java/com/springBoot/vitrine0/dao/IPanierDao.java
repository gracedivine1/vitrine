/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.dao;

import com.springBoot.vitrine0.entities.Panier;
import com.springBoot.vitrine0.entities.Produit;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DJEPANG
 */
public interface IPanierDao extends JpaRepository<Panier, Long> {

    public Collection<Panier> findPanierByProduit(Produit produit);

}
