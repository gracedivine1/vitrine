/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceImpl;

import com.springBoot.vitrine0.dao.IArtisanDao;
import com.springBoot.vitrine0.dao.ICatArtDao;
import com.springBoot.vitrine0.entities.CatArt;
import com.springBoot.vitrine0.serviceInterface.ICatArtService;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public class CatArtServiceImpl implements ICatArtService {

    @Autowired
    ICatArtDao iCatArtDao;

    @Override
    public CatArt saveOrUpdateCatArt(CatArt catArt) throws ServiceException {
        return iCatArtDao.save(catArt);
    }

    @Override
    public CatArt findCatArtByid(Long id) throws ServiceException {
        return iCatArtDao.findOne(id);
    }

    @Override
    public Collection<CatArt> findAllCatArt() throws ServiceException {
       return iCatArtDao.findAll();
    }

    @Override
    public void deleteCatArt(Long id) throws ServiceException {
        iCatArtDao.delete(id);
    }

    @Override
    public CatArt findCatArtByNomCatArt(String nomCatArt) throws ServiceException {
        return iCatArtDao.findByNomCatArt(nomCatArt);
    }


}
