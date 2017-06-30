/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.controller;

import com.springBoot.vitrine0.controller.utils.HeaderUtil;
import com.springBoot.vitrine0.entities.Commentaire;
import com.springBoot.vitrine0.serviceInterface.ICommentaireService;
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
public class CommentaireController {

    //Pour la gestion des érreurs
    private final Logger log = LoggerFactory.getLogger(CommentaireController.class);

    @Autowired
    ICommentaireService iCommentaireService;

    /**
     * POST /commentaire : Create a new commentaire.
     *
     * @param Commentaire the commentaire to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new commentaire, or with status 400 (Bad Request) if the commentaire has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/commentaires",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)//Pour que celà renvoit au format JSON
    public ResponseEntity<Commentaire> createCommentaire(@RequestBody Commentaire commentaire) throws URISyntaxException {//createCommentaire est un nom que je veux
        log.debug("REST request to save commentaire : {}", commentaire);
        if (commentaire.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("commentaire", "idexists", "A new commentaire cannot already have an ID")).body(null);
        }
        Commentaire result = iCommentaireService.saveOrUpdateCommentaire(commentaire);//La ligne la plus importante car elle use la couche service

        return ResponseEntity.created(new URI("/api/commentaires/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("commentaire", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /auteurs : Updates an existing author.
     *
     * @param commentaire the commentaire to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * commentaire, or with status 400 (Bad Request) if the commentaire is not
     * valid, or with status 500 (Internal Server Error) if the commentaire
     * couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/commentaires/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Commentaire> updateCommentaire(@RequestBody Commentaire commentaire) throws URISyntaxException {//<Commentaire> est l'esntité qu'on renvoyer
        log.debug("REST request to update commentaire : {}", commentaire);
        if (commentaire.getId() == null) {
            return createCommentaire(commentaire);
        }
        Commentaire result = iCommentaireService.saveOrUpdateCommentaire(commentaire);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("commentaire ", commentaire.getId().toString()))
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
    @RequestMapping(value = "/commentaires",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Commentaire>> getAllCommentaires()
            throws URISyntaxException {
        log.debug("REST request to get a page of artisan");
        Collection<Commentaire> collection = iCommentaireService.findAllCommentaire();
        return new ResponseEntity<>(collection, null, HttpStatus.OK);
    }

    /**
     * GET /commentaires/:id : get the "id" commentaire.
     *
     * @param id the id of the commentaire to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     * commentaire, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/commentaires/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Commentaire> getCommentaire(@PathVariable Long id) {
        log.debug("REST request to get artisan : {}", id);
        Commentaire result = iCommentaireService.findCommentaireByid(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    /**
     * DELETE /commentaire/:id : delete the "id" commentaire.
     *
     * @param id the id of the commentaire to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/commentaires/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCommentaire(@PathVariable Long id) {
        log.debug("REST request to delete commentaire : {}", id);
        iCommentaireService.deleteCommentaire(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("commentaire", id.toString())).build();
    }

}
