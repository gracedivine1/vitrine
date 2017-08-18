        //création de l'application du coté frontend en spécifiant les différent librairies ou bibliothèques a utiliser
var app = angular.module('vitrine',[//nom de l'application qui n'a pas forcement crè au backend
            
            'ngResource',//permet de prendre les réssources depuis le backend
             'ui.router',//module permet de gérer les états et les routes
             'ui.bootstrap'//permet d'utiliser certaines fonctions bootstrap avec angular
        
] );
