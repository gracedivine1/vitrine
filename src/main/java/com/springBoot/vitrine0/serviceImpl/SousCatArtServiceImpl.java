/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceImpl;

import com.springBoot.vitrine0.dao.ISousCatArtDao;
import com.springBoot.vitrine0.entities.SousCatArt;
import com.springBoot.vitrine0.serviceInterface.ISousCatArtService;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public class SousCatArtServiceImpl implements ISousCatArtService{
    
    @Autowired
    ISousCatArtDao iSousCatArtDao;
    
    @Override
    public SousCatArt saveOrUpdateSousCatArt(SousCatArt sousCatArt) throws ServiceException {
        return iSousCatArtDao.save(sousCatArt);
    }

    @Override
    public SousCatArt findSousCatArtByid(Long id) throws ServiceException {
        return iSousCatArtDao.findOne(id);
    }

    @Override
    public Collection<SousCatArt> findAllSousCatArt() throws ServiceException {
        return iSousCatArtDao.findAll();
    }

    @Override
    public void deleteSousCatArt(Long id) throws ServiceException {
        iSousCatArtDao.delete(id);
    }

    @Override
    public Collection<SousCatArt> findSousCatArtByNomSousCatArt(String nomSousCatArt) throws ServiceException {
        return iSousCatArtDao.findSousCatArtByNomSousCatArt(nomSousCatArt);
    }
    
}
