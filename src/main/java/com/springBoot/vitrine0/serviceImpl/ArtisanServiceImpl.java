/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceImpl;

import com.springBoot.vitrine0.dao.IArtisanDao;
import com.springBoot.vitrine0.entities.Artisan;
import com.springBoot.vitrine0.serviceInterface.IArtisanService;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public class ArtisanServiceImpl implements IArtisanService{
    
    @Autowired
    IArtisanDao iArtisanDao;

    @Override
    public Artisan saveOrUpdateArtisan(Artisan artisan) throws ServiceException {
        return iArtisanDao.save(artisan);
    }

    @Override
    public Artisan findArtisanByid(Long id) throws ServiceException {
        return iArtisanDao.findOne(id);
    }

    @Override
    public Collection<Artisan> findAllArtisan() throws ServiceException {
        return  iArtisanDao.findAll();
    }

    @Override
    public void deleteArtisan(Long id) throws ServiceException {
        iArtisanDao.delete(id);
    }
    
}
