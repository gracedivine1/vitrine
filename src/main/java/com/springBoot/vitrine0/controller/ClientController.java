/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.controller;

import com.springBoot.vitrine0.controller.utils.HeaderUtil;
import com.springBoot.vitrine0.entities.Client;
import com.springBoot.vitrine0.serviceInterface.IClientService;
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
public class ClientController {

    //Pour la gestion des érreurs
    private final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    IClientService iClientService;

    /**
     * POST /client : Create a new client.
     *
     * @param Client the client to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new client, or with status 400 (Bad Request) if the client has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/clients",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)//Pour que celà renvoit au format JSON
    public ResponseEntity<Client> createClient(@RequestBody Client client) throws URISyntaxException {//createClient est un nom que je veux
        log.debug("REST request to save client : {}", client);
        if (client.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("client", "idexists", "A new client cannot already have an ID")).body(null);
        }
        Client result = iClientService.saveOrUpdateClient(client);//La ligne la plus importante car elle use la couche service

        return ResponseEntity.created(new URI("/api/clients/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("client", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT /auteurs : Updates an existing author.
     *
     * @param client the client to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * client, or with status 400 (Bad Request) if the client is not valid, or
     * with status 500 (Internal Server Error) if the client couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/clients/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@RequestBody Client client) throws URISyntaxException {//<Client> est l'esntité qu'on renvoyer
        log.debug("REST request to update client : {}", client);
        if (client.getId() == null) {
            return createClient(client);
        }
        Client result = iClientService.saveOrUpdateClient(client);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("client ", client.getId().toString()))
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
    @RequestMapping(value = "/clients",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Client>> getAllClients()
            throws URISyntaxException {
        log.debug("REST request to get a page of artisan");
        Collection<Client> collection = iClientService.findAllClient();
        return new ResponseEntity<>(collection, null, HttpStatus.OK);
    }

    /**
     * GET /clients/:id : get the "id" client.
     *
     * @param id the id of the client to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the client,
     * or with status 404 (Not Found)
     */
    @RequestMapping(value = "/clients/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        log.debug("REST request to get artisan : {}", id);
        Client result = iClientService.findClientByid(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    /**
     * DELETE /client/:id : delete the "id" client.
     *
     * @param id the id of the client to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/clients/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        log.debug("REST request to delete client : {}", id);
        iClientService.deleteClient(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("client", id.toString())).build();
    }

}
