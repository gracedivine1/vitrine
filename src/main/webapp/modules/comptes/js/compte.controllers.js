(function () {
    'use strict';

    angular
            .module('vitrine')
            .controller('CompteController', CompteFontion)
            .controller('CompteDialogController', CompteDialogController)
            .controller('CompteDeleteController', CompteDeleteController);


    //============  Generale Author Controller  ======================//

    CompteFontion.$inject = ['CompteService'];//CompteService est la fabrique des services

    function CompteFontion(CompteService) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des comptes

            CompteService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.comptes = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }

    }
    ;

    //============  Author dialog controller ====================////dialog fait l'ajout et la modification

    CompteDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CompteService'];

    function CompteDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, CompteService) {

        var vm = this;

        vm.compte = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            console.log("mon non est ..." + vm.compte.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            //    vm.isSaving = true;
            if (vm.compte.id !== null) {
                console.log("vm.compte.id = " + vm.compte.id + " vm.compte.typeCompte = " + vm.compte.typeCompte);//pour le débogga, on peut le rétirer
                CompteService.update({id: vm.compte.id}, vm.compte, onSaveSuccess, onSaveError);
            } else {
                CompteService.save(vm.compte, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('vitrine :compteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }
    }
    ;

    //============  Author delete controller ====================//

    CompteDeleteController.$inject = ['$uibModalInstance', 'CompteService', 'entity'];

    function   CompteDeleteController($uibModalInstance, CompteService, entity) {
        var vm = this;

        vm.compte = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete(id) {
            CompteService.delete({id: id},
                    function () {
                        $uibModalInstance.close(true);
                    });
        }
    }

})();
