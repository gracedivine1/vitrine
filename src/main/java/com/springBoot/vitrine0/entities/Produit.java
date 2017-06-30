/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springBoot.vitrine0.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DJEPANG
 */
@Entity
public class Produit implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private  String nomProduit;
    
    @Column
    private  double prix;
    
    @Column
    private String imageURL;
    
    @Column
    private String description;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date datePublication;
    
    @JsonIgnore
    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY)
    private Collection<Panier> paniers;
    
    @JsonIgnore
    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY)
    private Collection<Commentaire> commentaires;
    
    @ManyToOne
    @JoinColumn
    private CatProduit catProduit;
    
     //Constructeurs

    public Produit() {
    }
    
    

//    public Produit(String nomProduit, double prix, String imageURL, String description, Date datePublication, Collection<Panier> paniers, Collection<Commentaire> commentaires, CatProduit catProduit) {
//        this.nomProduit = nomProduit;
//        this.prix = prix;
//        this.imageURL = imageURL;
//        this.description = description;
//        this.datePublication = datePublication;
//        this.paniers = paniers;
//        this.commentaires = commentaires;
//        this.catProduit = catProduit;
//    }

    public Produit(String nomProduit, double prix, String imageURL, String description, Date datePublication, Collection<Commentaire> commentaires, CatProduit catProduit) {
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.imageURL = imageURL;
        this.description = description;
        this.datePublication = datePublication;
        this.commentaires = commentaires;
        this.catProduit = catProduit;
    }

   


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }



    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public CatProduit getCatProduit() {
        return catProduit;
    }

    public void setCatProduit(CatProduit catProduit) {
        this.catProduit = catProduit;
    }

    public Collection<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Collection<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public Collection<Panier> getPaniers() {
        return paniers;
    }

    public void setPaniers(Collection<Panier> paniers) {
        this.paniers = paniers;
    }
    
    
    
}
