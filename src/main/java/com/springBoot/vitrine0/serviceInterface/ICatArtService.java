/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceInterface;

import com.springBoot.vitrine0.entities.CatArt;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public interface ICatArtService {

    public CatArt saveOrUpdateCatArt(CatArt catArt) throws ServiceException;

    public CatArt findCatArtByid(Long id) throws ServiceException;

    public Collection<CatArt> findAllCatArt() throws ServiceException;

    public void deleteCatArt(Long id) throws ServiceException;

    public CatArt findCatArtByNomCatArt(String nomCatArt) throws ServiceException;

}
