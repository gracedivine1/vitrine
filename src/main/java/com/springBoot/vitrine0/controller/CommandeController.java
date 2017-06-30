/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.controller;

import com.springBoot.vitrine0.controller.utils.HeaderUtil;
import com.springBoot.vitrine0.entities.Commande;
import com.springBoot.vitrine0.serviceInterface.ICommandeService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author DJEPANG
 */
@Controller
@RequestMapping(value = "/api")
public class CommandeController {

    //Pour la gestion des érreurs
    private final Logger log = LoggerFactory.getLogger(CommandeController.class);

    @Autowired
    ICommandeService iCommandeService;

    /**
     * POST /commande : Create a new commande.
     *
     * @param Commande the commande to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new commande, or with status 400 (Bad Request) if the commande has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/commandes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)//Pour que celà renvoit au format JSON
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) throws URISyntaxException {//createCommande est un nom que je veux
        log.debug("REST request to save commande : {}", commande);
        if (commande.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("commande", "idexists", "A new commande cannot already have an ID")).body(null);
        }
        Commande result = iCommandeService.saveOrUpdateCommande(commande);//La ligne la plus importante car elle use la couche service

        return ResponseEntity.created(new URI("/api/commandes/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("commande", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /auteurs : Updates an existing author.
     *
     * @param commande the commande to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * commande, or with status 400 (Bad Request) if the commande is not valid,
     * or with status 500 (Internal Server Error) if the commande couldnt be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/commandes/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Commande> updateCommande(@RequestBody Commande commande) throws URISyntaxException {//<Commande> est l'esntité qu'on renvoyer
        log.debug("REST request to update commande : {}", commande);
        if (commande.getId() == null) {
            return createCommande(commande);
        }
        Commande result = iCommandeService.saveOrUpdateCommande(commande);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("commande ", commande.getId().toString()))
                .body(result);
    }

    /**
     * GET /auteurs : get all the authors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of authors
     * in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @RequestMapping(value = "/commandes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Commande>> getAllCommandes()
            throws URISyntaxException {
        log.debug("REST request to get a page of artisan");
        Collection<Commande> collection = iCommandeService.findAllCommande();
        return new ResponseEntity<>(collection, null, HttpStatus.OK);
    }

    /**
     * GET /commandes/:id : get the "id" commande.
     *
     * @param id the id of the commande to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     * commande, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/commandes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Commande> getCommande(@PathVariable Long id) {
        log.debug("REST request to get artisan : {}", id);
        Commande result = iCommandeService.findCommandeByid(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    /**
     * DELETE /commande/:id : delete the "id" commande.
     *
     * @param id the id of the commande to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/commandes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        log.debug("REST request to delete commande : {}", id);
        iCommandeService.deleteCommande(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("commande", id.toString())).build();
    }

}
