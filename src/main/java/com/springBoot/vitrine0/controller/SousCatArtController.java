/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.controller;

import com.springBoot.vitrine0.controller.utils.HeaderUtil;
import com.springBoot.vitrine0.entities.SousCatArt;
import com.springBoot.vitrine0.serviceInterface.ISousCatArtService;
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
public class SousCatArtController {

    //Pour la gestion des érreurs
    private final Logger log = LoggerFactory.getLogger(SousCatArtController.class);

    @Autowired
    ISousCatArtService iSousCatArtService;

    /**
     * POST /sousCatArt : Create a new sousCatArt.
     *
     * @param SousCatArt the sousCatArt to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new sousCatArt, or with status 400 (Bad Request) if the sousCatArt has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/sousCatArts",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)//Pour que celà renvoit au format JSON
    public ResponseEntity<SousCatArt> createSousCatArt(@RequestBody SousCatArt sousCatArt) throws URISyntaxException {//createSousCatArt est un nom que je veux
        log.debug("REST request to save sousCatArt : {}", sousCatArt);
        if (sousCatArt.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("sousCatArt", "idexists", "A new sousCatArt cannot already have an ID")).body(null);
        }
           System.out.println(sousCatArt.getNomSousCatArt());
        SousCatArt result = iSousCatArtService.saveOrUpdateSousCatArt(sousCatArt);//La ligne la plus importante car elle use la couche service

        return ResponseEntity.created(new URI("/api/sousCatArts/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("sousCatArt", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /auteurs : Updates an existing author.
     *
     * @param sousCatArt the sousCatArt to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * sousCatArt, or with status 400 (Bad Request) if the sousCatArt is not
     * valid, or with status 500 (Internal Server Error) if the sousCatArt
     * couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/sousCatArts/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SousCatArt> updateSousCatArt(@RequestBody SousCatArt sousCatArt) throws URISyntaxException {//<SousCatArt> est l'esntité qu'on renvoyer
        log.debug("REST request to update sousCatArt : {}", sousCatArt);
        if (sousCatArt.getId() == null) {
            return createSousCatArt(sousCatArt);
        }
     
        SousCatArt result = iSousCatArtService.saveOrUpdateSousCatArt(sousCatArt);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("sousCatArt ", sousCatArt.getId().toString()))
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
    @RequestMapping(value = "/sousCatArts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SousCatArt>> getAllSousCatArts()
            throws URISyntaxException {
        log.debug("REST request to get a page of artisan");
        Collection<SousCatArt> collection = iSousCatArtService.findAllSousCatArt();
        return new ResponseEntity<>(collection, null, HttpStatus.OK);
    }

    /**
     * GET /sousCatArts/:id : get the "id" sousCatArt.
     *
     * @param id the id of the sousCatArt to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     * sousCatArt, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/sousCatArts/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SousCatArt> getSousCatArt(@PathVariable Long id) {
        log.debug("REST request to get artisan : {}", id);
        SousCatArt result = iSousCatArtService.findSousCatArtByid(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    /**
     * DELETE /sousCatArt/:id : delete the "id" sousCatArt.
     *
     * @param id the id of the sousCatArt to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/sousCatArts/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSousCatArt(@PathVariable Long id) {
        log.debug("REST request to delete sousCatArt : {}", id);
        iSousCatArtService.deleteSousCatArt(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("sousCatArt", id.toString())).build();
    }

}
