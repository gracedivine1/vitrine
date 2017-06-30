/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.serviceInterface;

import com.springBoot.vitrine0.entities.Commentaire;
import com.springBoot.vitrine0.entities.Produit;
import java.util.Collection;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
/**
 *
 * @author DJEPANG
 */
@Service
public interface ICommentaireService {
    
    public Commentaire saveOrUpdateCommentaire(Commentaire commentaire) throws ServiceException;

    public Commentaire findCommentaireByid(Long id) throws ServiceException;

    public Collection<Commentaire> findAllCommentaire() throws ServiceException;

    public void deleteCommentaire(Long id) throws ServiceException;

    public Collection<Commentaire> findCommentaireByProduit(Produit produit) throws ServiceException; 
    
}
