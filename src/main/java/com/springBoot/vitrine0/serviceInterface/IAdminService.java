/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceInterface;

import com.springBoot.vitrine0.entities.Administrateur;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;




/**
 *
 * @author DJEPANG
 */
@Service
public interface IAdminService {
    
    
    public Administrateur saveOrUpdateAdministrateur(Administrateur administrateur) throws ServiceException;

    public Administrateur findAdministrateurByid(Long id) throws ServiceException;

    public Collection<Administrateur> findAllAdministrateur() throws ServiceException;

    public void deleteAdministrateur(Long id) throws ServiceException;

    
}
