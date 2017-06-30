/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceInterface;

import com.springBoot.vitrine0.entities.Client;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;



/**
 *
 * @author DJEPANG
 */

@Service
public interface IClientService {
    
    
    public Client saveOrUpdateClient(Client client) throws ServiceException;

    public Client findClientByid(Long id) throws ServiceException;

    public Collection<Client> findAllClient() throws ServiceException;

    public void deleteClient(Long id) throws ServiceException;
    
}
