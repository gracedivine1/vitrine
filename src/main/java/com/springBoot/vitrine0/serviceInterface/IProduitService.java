/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceInterface;

import com.springBoot.vitrine0.entities.Produit;
import com.springBoot.vitrine0.entities.Produit;
import java.util.Collection;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public interface IProduitService {

    public Produit saveOrUpdateProduit(Produit produit) throws ServiceException;

    public Produit findProduitByid(Long id) throws ServiceException;

    public Collection<Produit> findAllProduit() throws ServiceException;

    public void deleteProduit(Long id) throws ServiceException;

    public Collection<Produit> findProduitByNomProduit(String nomProduit) throws ServiceException;

}
