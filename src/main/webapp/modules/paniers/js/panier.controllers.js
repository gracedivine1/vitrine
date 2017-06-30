(function () {
    'use strict';

    angular
            .module('vitrine')
            .controller('PanierController', PanierFontion)
            .controller('PanierDialogController', PanierDialogController)
            .controller('PanierDeleteController', PanierDeleteController);


    //============  Generale Author Controller  ======================//

    PanierFontion.$inject = ['PanierService'];//PanierService est la fabrique des services

    function PanierFontion(PanierService) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des paniers

            PanierService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.paniers = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }

    }
    ;

    //============  Author dialog controller ====================////dialog fait l'ajout et la modification

    PanierDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PanierService', 'CommandeService', 'ProduitService'];

    function PanierDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, PanierService, CommandeService, ProduitService) {

        var vm = this;

        vm.produit = entity;
        vm.commande = entity;
        vm.panier = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            console.log("mon non est ..." + vm.panier.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            //    vm.isSaving = true;
            if (vm.panier.id !== null) {
                console.log("vm.panier.id = " + vm.panier.id + " vm.Produit.nomProduit = " + vm.panier.produit.nomProduit + " vm.Commande.statut = " + vm.panier.commande.statut);//pour le débogga, on peut le rétirer
                PanierService.update({id: vm.panier.id}, vm.panier, onSaveSuccess, onSaveError);
            } else {
                PanierService.save(vm.panier, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('vitrine :panierUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }

        loadAll1();

        function loadAll1() {//Fontion qui affiche la liste des catArts

            ProduitService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                $scope.produits = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }

        loadAll2();

        function loadAll2() {//Fontion qui affiche la liste des catArts

            CommandeService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                $scope.commandes = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }
    }
    ;

    //============  Author delete controller ====================//

    PanierDeleteController.$inject = ['$uibModalInstance', 'PanierService', 'entity'];

    function   PanierDeleteController($uibModalInstance, PanierService, entity) {
        var vm = this;

        vm.panier = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete(id) {
            PanierService.delete({id: id},
                    function () {
                        $uibModalInstance.close(true);
                    });
        }
    }

})();
