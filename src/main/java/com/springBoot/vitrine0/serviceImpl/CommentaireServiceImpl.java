/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceImpl;

import com.springBoot.vitrine0.dao.ICommentaireDao;
import com.springBoot.vitrine0.entities.Commentaire;
import com.springBoot.vitrine0.entities.Produit;
import com.springBoot.vitrine0.serviceInterface.ICommentaireService;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DJEPANG
 */
@Service
public class CommentaireServiceImpl implements ICommentaireService{
    
    @Autowired
    ICommentaireDao iCommentaireDao;

    @Override
    public Commentaire saveOrUpdateCommentaire(Commentaire commentaire) throws ServiceException {
        return iCommentaireDao.save(commentaire);
    }

    @Override
    public Commentaire findCommentaireByid(Long id) throws ServiceException {
        return iCommentaireDao.findOne(id);
    }

    @Override
    public Collection<Commentaire> findAllCommentaire() throws ServiceException {
        return iCommentaireDao.findAll();
    }

    @Override
    public void deleteCommentaire(Long id) throws ServiceException {
       iCommentaireDao.delete(id);
    }

    @Override
    public Collection<Commentaire> findCommentaireByProduit(Produit produit) throws ServiceException {
       return iCommentaireDao.findCommentaireByProduit( produit);
    }
    
}
