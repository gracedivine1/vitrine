/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceInterface;

import com.springBoot.vitrine0.entities.Commande;
import java.util.Collection;
import java.util.Date;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public interface ICommandeService {

    public Commande saveOrUpdateCommande(Commande commande) throws ServiceException;

    public Commande findCommandeByid(Long id) throws ServiceException;

    public Collection<Commande> findAllCommande() throws ServiceException;

    public void deleteCommande(Long id) throws ServiceException;

    public Collection<Commande> findCommandeByDate(Date date) throws ServiceException;

}
