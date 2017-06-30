/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.controller;

import com.springBoot.vitrine0.controller.utils.HeaderUtil;
import com.springBoot.vitrine0.entities.Produit;
import com.springBoot.vitrine0.serviceInterface.IProduitService;
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
public class ProduitController {

    //Pour la gestion des érreurs
    private final Logger log = LoggerFactory.getLogger(ProduitController.class);

    @Autowired
    IProduitService iProduitService;

    /**
     * POST /produit : Create a new produit.
     *
     * @param Produit the produit to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new produit, or with status 400 (Bad Request) if the produit has already
     * an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/produits",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)//Pour que celà renvoit au format JSON
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) throws URISyntaxException {//createProduit est un nom que je veux
        log.debug("REST request to save produit : {}", produit);
        if (produit.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("produit", "idexists", "A new produit cannot already have an ID")).body(null);
        }
        Produit result = iProduitService.saveOrUpdateProduit(produit);//La ligne la plus importante car elle use la couche service

        return ResponseEntity.created(new URI("/api/produits/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("produit", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /auteurs : Updates an existing author.
     *
     * @param produit the produit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * produit, or with status 400 (Bad Request) if the produit is not valid, or
     * with status 500 (Internal Server Error) if the produit couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/produits/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produit> updateProduit(@RequestBody Produit produit) throws URISyntaxException {//<Produit> est l'esntité qu'on renvoyer
        log.debug("REST request to update produit : {}", produit);
        if (produit.getId() == null) {
            return createProduit(produit);
        }
        Produit result = iProduitService.saveOrUpdateProduit(produit);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("produit ", produit.getId().toString()))
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
    @RequestMapping(value = "/produits",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Produit>> getAllProduits()
            throws URISyntaxException {
        log.debug("REST request to get a page of artisan");
        Collection<Produit> collection = iProduitService.findAllProduit();
        return new ResponseEntity<>(collection, null, HttpStatus.OK);
    }

    /**
     * GET /produits/:id : get the "id" produit.
     *
     * @param id the id of the produit to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     * produit, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/produits/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produit> getProduit(@PathVariable Long id) {
        log.debug("REST request to get artisan : {}", id);
        Produit result = iProduitService.findProduitByid(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    /**
     * DELETE /produit/:id : delete the "id" produit.
     *
     * @param id the id of the produit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/produits/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        log.debug("REST request to delete produit : {}", id);
        iProduitService.deleteProduit(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("produit", id.toString())).build();
    }

}
