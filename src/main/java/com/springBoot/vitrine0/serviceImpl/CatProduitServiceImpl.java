/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceImpl;

import com.springBoot.vitrine0.dao.ICatProduitDao;
import com.springBoot.vitrine0.entities.CatProduit;
import com.springBoot.vitrine0.serviceInterface.ICatProduitService;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public class CatProduitServiceImpl implements ICatProduitService{
    
    @Autowired
    ICatProduitDao iCatProduitDao;

    @Override
    public CatProduit saveOrUpdateCatProduit(CatProduit catProduit) throws ServiceException {
        return iCatProduitDao.save(catProduit);
    }

    @Override
    public CatProduit findCatProduitByid(Long id) throws ServiceException {
        return iCatProduitDao.findOne(id);
    }

    @Override
    public Collection<CatProduit> findAllCatProduit() throws ServiceException {
        return iCatProduitDao.findAll();
    }

    @Override
    public void deleteCatProduit(Long id) throws ServiceException {
       iCatProduitDao.delete(id);
    }

    @Override
    public CatProduit findCatProduitByNomCatProduit(String nomCatProduit) throws ServiceException {
        return iCatProduitDao.findByNomCatProduit(nomCatProduit);
    }
    
}
