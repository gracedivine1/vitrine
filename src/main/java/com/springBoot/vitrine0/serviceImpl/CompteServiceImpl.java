/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceImpl;

import com.springBoot.vitrine0.dao.ICompteDao;
import com.springBoot.vitrine0.entities.Compte;
import com.springBoot.vitrine0.serviceInterface.ICompteService;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public class CompteServiceImpl implements ICompteService{
    
    @Autowired
    ICompteDao iCompteDao;

    @Override
    public Compte saveOrUpdateCompte(Compte compte) throws ServiceException {
         return iCompteDao.save(compte);
    }

    @Override
    public Compte findCompteByid(Long id) throws ServiceException {
         return iCompteDao.findOne(id);
    }

    @Override
    public Collection<Compte> findAllCompte() throws ServiceException {
        return iCompteDao.findAll();
    }

    @Override
    public void deleteCompte(Long id) throws ServiceException {
        iCompteDao.delete(id);
    }

    @Override
    public Collection<Compte> findCompteByTypeCompte(int typeCompte) throws ServiceException {
        return iCompteDao.findCompteByTypeCompte(typeCompte);
    }
    
}
