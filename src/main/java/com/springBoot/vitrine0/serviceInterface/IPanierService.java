/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceInterface;

import com.springBoot.vitrine0.entities.Panier;
import com.springBoot.vitrine0.entities.Produit;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public interface IPanierService {

    public Panier saveOrUpdatePanier(Panier panier) throws ServiceException;

    public Panier findPanierByid(Long id) throws ServiceException;

    public Collection<Panier> findAllPanier() throws ServiceException;

    public void deletePanier(Long id) throws ServiceException;

    public Collection<Panier> findPanierByProduit(Produit produit) throws ServiceException;

}
