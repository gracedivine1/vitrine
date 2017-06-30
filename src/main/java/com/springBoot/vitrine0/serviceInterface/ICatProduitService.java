/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceInterface;

import com.springBoot.vitrine0.entities.CatProduit;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public interface ICatProduitService {
    
    public CatProduit saveOrUpdateCatProduit(CatProduit catProduit) throws ServiceException;

    public CatProduit findCatProduitByid(Long id) throws ServiceException;

    public Collection<CatProduit> findAllCatProduit() throws ServiceException;

    public void deleteCatProduit(Long id) throws ServiceException;

    public CatProduit findCatProduitByNomCatProduit(String nomCatProduit) throws ServiceException;   
    
}
