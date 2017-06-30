/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceImpl;

import com.springBoot.vitrine0.dao.IClientDao;
import com.springBoot.vitrine0.entities.Client;
import com.springBoot.vitrine0.serviceInterface.IClientService;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public class ClientServiceImpl implements IClientService{
    
    @Autowired
    IClientDao iClientDao;

    @Override
    public Client saveOrUpdateClient(Client client) throws ServiceException {
        return iClientDao.save(client);
    }

    @Override
    public Client findClientByid(Long id) throws ServiceException {
        return iClientDao.findOne(id);
    }

    @Override
    public Collection<Client> findAllClient() throws ServiceException {
       return iClientDao.findAll();
    }

    @Override
    public void deleteClient(Long id) throws ServiceException {
          iClientDao.delete(id);
    }
    
}
