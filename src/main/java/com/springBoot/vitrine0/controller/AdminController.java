/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.controller;

import com.springBoot.vitrine0.controller.utils.HeaderUtil;
import com.springBoot.vitrine0.entities.Administrateur;
import com.springBoot.vitrine0.entities.Administrateur;
import com.springBoot.vitrine0.serviceInterface.IAdminService;
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
public class AdminController {

    //Pour la gestion des érreurs
    private final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    IAdminService iAdminService;

    /**
     * POST /admin : Create a new admin.
     *
     * @param Admin the admin to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new admin, or with status 400 (Bad Request) if the admin has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/admins",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)//Pour que celà renvoit au format JSON
    public ResponseEntity<Administrateur> createAdministrateur(@RequestBody Administrateur admin) throws URISyntaxException {//createAdministrateur est un nom que je veux
        log.debug("REST request to save admin : {}", admin);
        if (admin.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("admin", "idexists", "A new admin cannot already have an ID")).body(null);
        }
        Administrateur result = iAdminService.saveOrUpdateAdministrateur(admin);//La ligne la plus importante car elle use la couche service

        return ResponseEntity.created(new URI("/api/admins/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("admin", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /auteurs : Updates an existing author.
     *
     * @param admin the admin to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * admin, or with status 400 (Bad Request) if the admin is not valid, or
     * with status 500 (Internal Server Error) if the admin couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/admins/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrateur> updateAdministrateur(@RequestBody Administrateur admin) throws URISyntaxException {//<Administrateur> est l'esntité qu'on renvoyer
        log.debug("REST request to update admin : {}", admin);
        if (admin.getId() == null) {
            return createAdministrateur(admin);
        }
        System.out.println(admin.getEmail());
        Administrateur result = iAdminService.saveOrUpdateAdministrateur(admin);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("admin ", admin.getId().toString()))
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
    @RequestMapping(value = "/admins",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Administrateur>> getAllAdministrateurs()
            throws URISyntaxException {
        log.debug("REST request to get a page of administrateur");
        Collection<Administrateur> collection = iAdminService.findAllAdministrateur();
        return new ResponseEntity<>(collection, null, HttpStatus.OK);
    }

    /**
     * GET /admins/:id : get the "id" admin.
     *
     * @param id the id of the admin to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the admin,
     * or with status 404 (Not Found)
     */
    @RequestMapping(value = "/admins/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrateur> getAdministrateur(@PathVariable Long id) {
        log.debug("REST request to get administrateur : {}", id);
        Administrateur result = iAdminService.findAdministrateurByid(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    /**
     * DELETE /admin/:id : delete the "id" admin.
     *
     * @param id the id of the admin to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/admins/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Long id) {
        log.debug("REST request to delete admin : {}", id);
        iAdminService.deleteAdministrateur(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("admin", id.toString())).build();
    }

}
