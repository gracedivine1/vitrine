(function () {
    'use strict';

    angular
            .module('vitrine')
            .controller('ProduitController', ProduitFontion)
            .controller('ProduitDialogController', ProduitDialogController)
            .controller('ProduitDeleteController', ProduitDeleteController);


    //============  Generale Author Controller  ======================//

    ProduitFontion.$inject = ['ProduitService'];//ProduitService est la fabrique des services

    function ProduitFontion(ProduitService) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des produits

            ProduitService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.produits = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }

    }
    ;

    //============  Author dialog controller ====================////dialog fait l'ajout et la modification

    ProduitDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ProduitService','CatProduitService'];

    function ProduitDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, ProduitService,CatProduitService) {

        var vm = this;

        vm.produit = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            console.log("mon non est ..." + vm.produit.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            //    vm.isSaving = true;
            if (vm.produit.id !== null) {
                console.log("vm.produit.id = " + vm.produit.id + " vm.produit.nom = " + vm.produit.nom);//pour le débogga, on peut le rétirer
                ProduitService.update({id: vm.produit.id}, vm.produit, onSaveSuccess, onSaveError);
            } else {
                ProduitService.save(vm.produit, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('vitrine :produitUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }
    }
    ;

    //============  Author delete controller ====================//

    ProduitDeleteController.$inject = ['$uibModalInstance', 'ProduitService', 'entity'];

    function   ProduitDeleteController($uibModalInstance, ProduitService, entity) {
        var vm = this;

        vm.produit = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete(id) {
            ProduitService.delete({id: id},
                    function () {
                        $uibModalInstance.close(true);
                    });
        }
    }

})();
