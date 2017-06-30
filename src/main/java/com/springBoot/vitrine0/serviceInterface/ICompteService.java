/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceInterface;

import com.springBoot.vitrine0.entities.Compte;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public interface ICompteService {

    public Compte saveOrUpdateCompte(Compte compte) throws ServiceException;

    public Compte findCompteByid(Long id) throws ServiceException;

    public Collection<Compte> findAllCompte() throws ServiceException;

    public void deleteCompte(Long id) throws ServiceException;

    public Collection<Compte> findCompteByTypeCompte(int typeCompte) throws ServiceException;

}
