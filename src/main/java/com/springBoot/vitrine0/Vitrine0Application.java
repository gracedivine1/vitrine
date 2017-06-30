package com.springBoot.vitrine0;

import com.springBoot.vitrine0.dao.IAdminDao;
import com.springBoot.vitrine0.dao.IArtisanDao;
import com.springBoot.vitrine0.dao.ICatArtDao;
import com.springBoot.vitrine0.dao.IClientDao;
import com.springBoot.vitrine0.dao.ISousCatArtDao;
import com.springBoot.vitrine0.entities.Administrateur;
import com.springBoot.vitrine0.entities.Artisan;
import com.springBoot.vitrine0.entities.CatArt;
import com.springBoot.vitrine0.entities.Client;
import com.springBoot.vitrine0.entities.SousCatArt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Vitrine0Application {
//    @Autowired
//    private static IClientDao iclientdao;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Vitrine0Application.class, args);
       
        //Création d'un administrateur
////        
//         IAdminDao iAdminDao = ctx.getBean(IAdminDao.class);
//         Administrateur admin1 = iAdminDao.save(new Administrateur("magni", "clarisse", "clarisseLooo", "clarisse", "clarisse@gmail.com", "6773446", "douala"));
//        iAdminDao.save(admin1);
//        Administrateur admin2 = iAdminDao.save(new Administrateur("possi", "bled", "bledLoooo", "bled", "bled@gmail.com", "698435687", "bafoussam"));
//        iAdminDao.save(admin2);

//        IArtisanDao iArtisanDao = ctx.getBean(IArtisanDao.class);
//        Artisan artisan1 = iArtisanDao.save(new Artisan("narcisse", "nzapa", "narcisse56", "narcisse", "narciss@gmail.com", "12332143", "maroua"));
//        iArtisanDao.save(artisan1);
//        Artisan artisan2 = iArtisanDao.save(new Artisan("djepang", "lusianne", "lusianne57", "lusianne", "lusianne@gmail.com", "54656754", "maroua"));
//        iArtisanDao.save(artisan2);
//     IClientDao iClientDao = ctx.getBean(IClientDao.class);      
//        Client client3 = iClientDao.save(new Client("magni", "clarisse", "clarisse5", "clarisse", "clarisse@gmail.com", "6773446", "douala"));
//        iClientDao.save(client3);
//        Client client4 = iClientDao.save(new Client("possi", "bled", "bled5", "bled", "bled@gmail.com", "698435687", "bafoussam"));
//        iClientDao.save(client4);
//
////Ajout des catégories des artisants
//        ICatArtDao iCatArtDao = ctx.getBean(ICatArtDao.class);
//        CatArt catArt1 = iCatArtDao.save(new CatArt("Artisanat de production "));
//        iCatArtDao.save(catArt1);
//        CatArt catArt2 = iCatArtDao.save(new CatArt("Artisanat de service"));
//        iCatArtDao.save(catArt2);
//        CatArt catArt3 = iCatArtDao.save(new CatArt("Artisanat d’art"));
//        iCatArtDao.save(catArt3);

        //Ajout des sous cathégories des artisants
//        ISousCatArtDao iSousCatArtDao = ctx.getBean(ISousCatArtDao.class);
////Première catégorie(Artisanat de production )
//        SousCatArt souscatArt1 = iSousCatArtDao.save(new SousCatArt("Maroquinerie"));
//        iSousCatArtDao.save(souscatArt1);
//
//        SousCatArt souscatArt2 = iSousCatArtDao.save(new SousCatArt("Menuiserie-bois"));
//        iSousCatArtDao.save(souscatArt2);
////Deuxieme catégorie(Artisanat de service )
//        SousCatArt souscatArt3 = iSousCatArtDao.save(new SousCatArt("Bureautique"));
//        iSousCatArtDao.save(souscatArt3);
//
//        SousCatArt souscatArt4 = iSousCatArtDao.save(new SousCatArt("Photographie"));
//        iSousCatArtDao.save(souscatArt4);
////Troisième catégorie(Artisanat d’art )
//        SousCatArt souscatArt5 = iSousCatArtDao.save(new SousCatArt("Sculpture"));
//        iSousCatArtDao.save(souscatArt5);
//
//        SousCatArt souscatArt6 = iSousCatArtDao.save(new SousCatArt("Peinture"));
//        iSousCatArtDao.save(souscatArt6);

    }

}
