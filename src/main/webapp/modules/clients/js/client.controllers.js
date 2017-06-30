(function () {
    'use strict';

    angular
            .module('vitrine')
            .controller('ClientController', ClientFontion)
            .controller('ClientDialogController', ClientDialogController)
            .controller('ClientDeleteController', ClientDeleteController);


    //============  Generale Author Controller  ======================//

    ClientFontion.$inject = ['ClientService'];//ClientService est la fabrique des services

    function ClientFontion(ClientService) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des clients

            ClientService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.clients = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }

    }
    ;

    //============  Author dialog controller ====================////dialog fait l'ajout et la modification

    ClientDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ClientService'];

    function ClientDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, ClientService) {

        var vm = this;

        vm.client = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            console.log("mon non est ..." + vm.client.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            //    vm.isSaving = true;
            if (vm.client.id !== null) {
                console.log("vm.client.id = " + vm.client.id + " vm.sousCatArt.nom = " + vm.client.sousCatArt.nomSousCatArt);//pour le débogga, on peut le rétirer
                ClientService.update({id: vm.client.id}, vm.client, onSaveSuccess, onSaveError);
            } else {
                ClientService.save(vm.client, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('vitrine :clientUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }
    }
    ;

    //============  Author delete controller ====================//

    ClientDeleteController.$inject = ['$uibModalInstance', 'ClientService', 'entity'];

    function   ClientDeleteController($uibModalInstance, ClientService, entity) {
        var vm = this;

        vm.client = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete(id) {
            ClientService.delete({id: id},
                    function () {
                        $uibModalInstance.close(true);
                    });
        }
    }

})();
