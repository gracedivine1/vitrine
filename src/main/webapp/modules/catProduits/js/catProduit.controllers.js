(function () {
    'use strict';

    angular
            .module('vitrine')
            .controller('CatProduitController', CatProduitFontion)
            .controller('CatProduitDialogController', CatProduitDialogController)
            .controller('CatProduitDeleteController', CatProduitDeleteController);


    //============  Generale Author Controller  ======================//

    CatProduitFontion.$inject = ['CatProduitService'];//CatProduitService est la fabrique des services

    function CatProduitFontion(CatProduitService) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des catProduits

            CatProduitService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.catProduits = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log("jai une erreur");
            }
        }

    }
    ;

    //============  Author dialog controller ====================////dialog fait l'ajout et la modification

    CatProduitDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CatProduitService'];

    function CatProduitDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, CatProduitService) {

        var vm = this;

        vm.catProduit = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            console.log("mon non est ..." + vm.catProduit.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            //    vm.isSaving = true;
            if (vm.catProduit.id !== null) {
                console.log("vm.catProduit.id = " + vm.catProduit.id + " vm.catProduit.nomCatProduit = " + vm.catProduit.nomCatProduit);//pour le débogga, on peut le rétirer
                CatProduitService.update({id: vm.catProduit.id}, vm.catProduit, onSaveSuccess, onSaveError);
            } else {
                CatProduitService.save(vm.catProduit, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('vitrine :catProduitUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }
    }
    ;

    //============  Author delete controller ====================//

    CatProduitDeleteController.$inject = ['$uibModalInstance', 'CatProduitService', 'entity'];

    function   CatProduitDeleteController($uibModalInstance, CatProduitService, entity) {
        var vm = this;

        vm.catProduit = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete(id) {
            CatProduitService.delete({id: id},
                    function () {
                        $uibModalInstance.close(true);
                    });
        }
    }

})();
