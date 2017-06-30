/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceImpl;

import com.springBoot.vitrine0.dao.IProduitDao;
import com.springBoot.vitrine0.entities.Produit;
import com.springBoot.vitrine0.serviceInterface.IProduitService;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public class ProduitServiceImpl implements IProduitService{

    @Autowired
    IProduitDao iProduitDao;
    
    @Override
    public Produit saveOrUpdateProduit(Produit produit) throws ServiceException {
        return iProduitDao.save(produit);
    }

    @Override
    public Produit findProduitByid(Long id) throws ServiceException {
        return iProduitDao.findOne(id);
    }

    @Override
    public Collection<Produit> findAllProduit() throws ServiceException {
        return iProduitDao.findAll();
    }

    @Override
    public void deleteProduit(Long id) throws ServiceException {
         iProduitDao.delete(id);
    }

    @Override
    public Collection<Produit> findProduitByNomProduit(String nomProduit) throws ServiceException {
        return iProduitDao.findProduitByNomProduit(nomProduit);
    }
    
    
}
