/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceImpl;

import com.springBoot.vitrine0.dao.IAdminDao;
import com.springBoot.vitrine0.entities.Administrateur;
import com.springBoot.vitrine0.serviceInterface.IAdminService;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public class AdminServiceImpl implements IAdminService{
    
    @Autowired
    IAdminDao iAdminDao;

    @Override
    public Administrateur saveOrUpdateAdministrateur(Administrateur administrateur) throws ServiceException {
       return iAdminDao.save(administrateur);
    }

    @Override
    public Administrateur findAdministrateurByid(Long id) throws ServiceException {
       return iAdminDao.findOne(id);
    }

    @Override
    public Collection<Administrateur> findAllAdministrateur() throws ServiceException {
        return iAdminDao.findAll();
    }

    @Override
    public void deleteAdministrateur(Long id) throws ServiceException {
        iAdminDao.delete(id);
    }
    
}
