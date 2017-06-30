/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceImpl;

import com.springBoot.vitrine0.dao.ICommandeDao;
import com.springBoot.vitrine0.entities.Commande;
import com.springBoot.vitrine0.serviceInterface.ICommandeService;
import java.util.Collection;
import java.util.Date;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public class CommandeServiceImpl implements ICommandeService{
    
    @Autowired
    ICommandeDao iCommandeDao;

    @Override
    public Commande saveOrUpdateCommande(Commande commande) throws ServiceException {
        return iCommandeDao.save(commande);
    }

    @Override
    public Commande findCommandeByid(Long id) throws ServiceException {
        return iCommandeDao.findOne(id);
    }

    @Override
    public Collection<Commande> findAllCommande() throws ServiceException {
        return iCommandeDao.findAll();
    }

    @Override
    public void deleteCommande(Long id) throws ServiceException {
        iCommandeDao.delete(id);
    }

    @Override
    public Collection<Commande> findCommandeByDate(Date date) throws ServiceException {
        return iCommandeDao.findCommandeByDate(date);
    }
    
}
