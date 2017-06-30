/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.controller;

import com.springBoot.vitrine0.controller.utils.HeaderUtil;
import com.springBoot.vitrine0.entities.Compte;
import com.springBoot.vitrine0.serviceInterface.ICompteService;
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
public class CompteController {

    //Pour la gestion des érreurs
    private final Logger log = LoggerFactory.getLogger(CompteController.class);

    @Autowired
    ICompteService iCompteService;

    /**
     * POST /compte : Create a new compte.
     *
     * @param Compte the compte to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new compte, or with status 400 (Bad Request) if the compte has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/comptes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)//Pour que celà renvoit au format JSON
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) throws URISyntaxException {//createCompte est un nom que je veux
        log.debug("REST request to save compte : {}", compte);
        if (compte.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("compte", "idexists", "A new compte cannot already have an ID")).body(null);
        }
        Compte result = iCompteService.saveOrUpdateCompte(compte);//La ligne la plus importante car elle use la couche service

        return ResponseEntity.created(new URI("/api/comptes/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("compte", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /auteurs : Updates an existing author.
     *
     * @param compte the compte to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * compte, or with status 400 (Bad Request) if the compte is not valid, or
     * with status 500 (Internal Server Error) if the compte couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/comptes/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Compte> updateCompte(@RequestBody Compte compte) throws URISyntaxException {//<Compte> est l'esntité qu'on renvoyer
        log.debug("REST request to update compte : {}", compte);
        if (compte.getId() == null) {
            return createCompte(compte);
        }
        Compte result = iCompteService.saveOrUpdateCompte(compte);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("compte ", compte.getId().toString()))
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
    @RequestMapping(value = "/comptes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Compte>> getAllComptes()
            throws URISyntaxException {
        log.debug("REST request to get a page of compte");
        Collection<Compte> collection = iCompteService.findAllCompte();
        return new ResponseEntity<>(collection, null, HttpStatus.OK);
    }

    /**
     * GET /comptes/:id : get the "id" compte.
     *
     * @param id the id of the compte to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the compte,
     * or with status 404 (Not Found)
     */
    @RequestMapping(value = "/comptes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Compte> getCompte(@PathVariable Long id) {
        log.debug("REST request to get compte : {}", id);
        Compte result = iCompteService.findCompteByid(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    /**
     * DELETE /compte/:id : delete the "id" compte.
     *
     * @param id the id of the compte to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/comptes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        log.debug("REST request to delete compte : {}", id);
        iCompteService.deleteCompte(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("compte", id.toString())).build();
    }

}
