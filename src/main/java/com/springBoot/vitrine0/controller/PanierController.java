/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.controller;

import com.springBoot.vitrine0.controller.utils.HeaderUtil;
import com.springBoot.vitrine0.entities.Panier;
import com.springBoot.vitrine0.serviceInterface.IPanierService;
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
public class PanierController {

    //Pour la gestion des érreurs
    private final Logger log = LoggerFactory.getLogger(PanierController.class);

    @Autowired
    IPanierService iPanierService;

    /**
     * POST /panier : Create a new panier.
     *
     * @param Panier the panier to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new panier, or with status 400 (Bad Request) if the panier has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/paniers",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)//Pour que celà renvoit au format JSON
    public ResponseEntity<Panier> createPanier(@RequestBody Panier panier) throws URISyntaxException {//createPanier est un nom que je veux
        log.debug("REST request to save panier : {}", panier);
        if (panier.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("panier", "idexists", "A new panier cannot already have an ID")).body(null);
        }
        Panier result = iPanierService.saveOrUpdatePanier(panier);//La ligne la plus importante car elle use la couche service

        return ResponseEntity.created(new URI("/api/paniers/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("panier", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /auteurs : Updates an existing author.
     *
     * @param panier the panier to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * panier, or with status 400 (Bad Request) if the panier is not valid, or
     * with status 500 (Internal Server Error) if the panier couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/paniers/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Panier> updatePanier(@RequestBody Panier panier) throws URISyntaxException {//<Panier> est l'esntité qu'on renvoyer
        log.debug("REST request to update panier : {}", panier);
        if (panier.getId() == null) {
            return createPanier(panier);
        }
        Panier result = iPanierService.saveOrUpdatePanier(panier);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("panier ", panier.getId().toString()))
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
    @RequestMapping(value = "/paniers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Panier>> getAllPaniers()
            throws URISyntaxException {
        log.debug("REST request to get a page of artisan");
        Collection<Panier> collection = iPanierService.findAllPanier();
        return new ResponseEntity<>(collection, null, HttpStatus.OK);
    }

    /**
     * GET /paniers/:id : get the "id" panier.
     *
     * @param id the id of the panier to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the panier,
     * or with status 404 (Not Found)
     */
    @RequestMapping(value = "/paniers/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Panier> getPanier(@PathVariable Long id) {
        log.debug("REST request to get artisan : {}", id);
        Panier result = iPanierService.findPanierByid(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    /**
     * DELETE /panier/:id : delete the "id" panier.
     *
     * @param id the id of the panier to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/paniers/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletePanier(@PathVariable Long id) {
        log.debug("REST request to delete panier : {}", id);
        iPanierService.deletePanier(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("panier", id.toString())).build();
    }

}
