/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.controller;

import com.springBoot.vitrine0.controller.utils.HeaderUtil;
import com.springBoot.vitrine0.entities.CatArt;
import com.springBoot.vitrine0.serviceInterface.ICatArtService;
import java.net.URI;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
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
public class CatArtController {

    //Pour la gestion des érreurs
    private final Logger log = LoggerFactory.getLogger(CatArtController.class);

    @Autowired
    ICatArtService iCatArtService;

    /**
     * POST /catArt : Create a new catArt.
     *
     * @param categoriArtisant the catArt to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new catArt, or with status 400 (Bad Request) if the catArt has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/catArts",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)//Pour que celà renvoit au format JSON
    public ResponseEntity<CatArt> createCatArt(@RequestBody CatArt catArt) throws URISyntaxException {//createCatArt est un nom que je veux
        log.debug("REST request to save catArt : {}", catArt);
        if (catArt.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("catArt", "idexists", "A new catArt cannot already have an ID")).body(null);
        }
        CatArt result = iCatArtService.saveOrUpdateCatArt(catArt);//La ligne la plus importante car elle use la couche service

        return ResponseEntity.created(new URI("/api/catArts/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("catArt", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /auteurs : Updates an existing author.
     *
     * @param catArt the catArt to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * catArt, or with status 400 (Bad Request) if the catArt is not valid, or
     * with status 500 (Internal Server Error) if the catArt couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/catArts/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CatArt> updateCatArtisan(@RequestBody CatArt catArt) throws URISyntaxException {//<CatArt> est l'esntité qu'on renvoyer
        log.debug("REST request to update catArt : {}", catArt);
        if (catArt.getId() == null) {
            return createCatArt(catArt);
        }
        CatArt result = iCatArtService.saveOrUpdateCatArt(catArt);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("catArt ", catArt.getId().toString()))
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
    @RequestMapping(value = "/catArts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CatArt>> getAllCatArts()
            throws URISyntaxException {
        log.debug("REST request to get a page of cathegorieArtisant");
        Collection<CatArt> collection = iCatArtService.findAllCatArt();
        return new ResponseEntity<>(collection, null, HttpStatus.OK);
    }

    /**
     * GET /catArts/:id : get the "id" catArt.
     *
     * @param id the id of the catArt to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catArt,
     * or with status 404 (Not Found)
     */
    @RequestMapping(value = "/catArts/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CatArt> getCatArt(@PathVariable Long id) {
        log.debug("REST request to get categorieArtisant : {}", id);
        CatArt result = iCatArtService.findCatArtByid(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    /**
     * DELETE /catArt/:id : delete the "id" catArt.
     *
     * @param id the id of the catArt to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/catArts/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCatArt(@PathVariable Long id) {
        log.debug("REST request to delete catArt : {}", id);
        iCatArtService.deleteCatArt(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("catArt", id.toString())).build();
    }
}
