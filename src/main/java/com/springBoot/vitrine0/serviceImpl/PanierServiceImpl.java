/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceImpl;

import com.springBoot.vitrine0.dao.IPanierDao;
import com.springBoot.vitrine0.entities.Panier;
import com.springBoot.vitrine0.entities.Produit;
import com.springBoot.vitrine0.serviceInterface.IPanierService;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public class PanierServiceImpl implements IPanierService{
    
    @Autowired
    IPanierDao iPanierDao;

    @Override
    public Panier saveOrUpdatePanier(Panier panier) throws ServiceException {
         return iPanierDao.save(panier);
    }

    @Override
    public Panier findPanierByid(Long id) throws ServiceException {
        return iPanierDao.findOne(id);
    }

    @Override
    public Collection<Panier> findAllPanier() throws ServiceException {
        return iPanierDao.findAll();
    }

    @Override
    public void deletePanier(Long id) throws ServiceException {
        iPanierDao.delete(id);
    }

    @Override
    public Collection<Panier> findPanierByProduit(Produit produit) throws ServiceException {
         return iPanierDao.findPanierByProduit(produit);
    }
    
}
