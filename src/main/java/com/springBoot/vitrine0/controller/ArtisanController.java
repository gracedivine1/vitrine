/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.controller;

import com.springBoot.vitrine0.controller.utils.HeaderUtil;
import com.springBoot.vitrine0.entities.Artisan;
import com.springBoot.vitrine0.serviceInterface.IArtisanService;
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
public class ArtisanController {

    //Pour la gestion des érreurs
    private final Logger log = LoggerFactory.getLogger(ArtisanController.class);

    @Autowired
    IArtisanService iArtisanService;

    /**
     * POST /artisan : Create a new artisan.
     *
     * @param Admin the artisan to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new artisan, or with status 400 (Bad Request) if the artisan has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/artisans",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)//Pour que celà renvoit au format JSON
    public ResponseEntity<Artisan> createArtisan(@RequestBody Artisan artisan) throws URISyntaxException {//createArtisan est un nom que je veux
        log.debug("REST request to save artisan : {}", artisan);
        if (artisan.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("artisan", "idexists", "A new artisan cannot already have an ID")).body(null);
        }
        Artisan result = iArtisanService.saveOrUpdateArtisan(artisan);//La ligne la plus importante car elle use la couche service

        return ResponseEntity.created(new URI("/api/artisans/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("artisan", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /auteurs : Updates an existing author.
     *
     * @param artisan the artisan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * artisan, or with status 400 (Bad Request) if the artisan is not valid, or
     * with status 500 (Internal Server Error) if the artisan couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/artisans/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artisan> updateArtisan(@RequestBody Artisan artisan) throws URISyntaxException {//<Artisan> est l'esntité qu'on renvoyer
        log.debug("REST request to update artisan : {}", artisan);
        if (artisan.getId() == null) {
            return createArtisan(artisan);
        }
        Artisan result = iArtisanService.saveOrUpdateArtisan(artisan);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("artisan ", artisan.getId().toString()))
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
    @RequestMapping(value = "/artisans",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Artisan>> getAllArtisans()
            throws URISyntaxException {
        log.debug("REST request to get a page of artisan");
        Collection<Artisan> collection = iArtisanService.findAllArtisan();
        return new ResponseEntity<>(collection, null, HttpStatus.OK);
    }

    /**
     * GET /artisans/:id : get the "id" artisan.
     *
     * @param id the id of the artisan to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the artisan,
     * or with status 404 (Not Found)
     */
    @RequestMapping(value = "/artisans/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artisan> getArtisan(@PathVariable Long id) {
        log.debug("REST request to get artisan : {}", id);
        Artisan result = iArtisanService.findArtisanByid(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    /**
     * DELETE /artisan/:id : delete the "id" artisan.
     *
     * @param id the id of the artisan to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/artisans/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteArtisan(@PathVariable Long id) {
        log.debug("REST request to delete artisan : {}", id);
        iArtisanService.deleteArtisan(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("artisan", id.toString())).build();
    }

}
