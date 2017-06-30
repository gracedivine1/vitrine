/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceInterface;

import com.springBoot.vitrine0.entities.Artisan;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;




/**
 *
 * @author DJEPANG
 */
@Service
public interface IArtisanService {
    
    
    public Artisan saveOrUpdateArtisan(Artisan artisan) throws ServiceException;

    public Artisan findArtisanByid(Long id) throws ServiceException;

    public Collection<Artisan> findAllArtisan() throws ServiceException;

    public void deleteArtisan(Long id) throws ServiceException;
    
}
