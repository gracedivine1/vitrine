/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceInterface;

import com.springBoot.vitrine0.entities.SousCatArt;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;


/**
 *
 * @author DJEPANG
 */
@Service
public interface ISousCatArtService {
    
    public SousCatArt saveOrUpdateSousCatArt(SousCatArt sousCatArt) throws ServiceException;

    public SousCatArt findSousCatArtByid(Long id) throws ServiceException;

    public Collection<SousCatArt> findAllSousCatArt() throws ServiceException;

    public void deleteSousCatArt(Long id) throws ServiceException;

    public Collection<SousCatArt> findSousCatArtByNomSousCatArt(String nomSousCatArt) throws ServiceException; 
    
}
