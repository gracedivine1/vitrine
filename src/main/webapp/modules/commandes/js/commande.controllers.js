(function () {
    'use strict';

    angular
            .module('vitrine')
            .controller('CommandeController', CommandeFontion)
            .controller('CommandeDialogController', CommandeDialogController)
            .controller('CommandeDeleteController', CommandeDeleteController);


    //============  Generale Author Controller  ======================//

    CommandeFontion.$inject = ['CommandeService'];//CommandeService est la fabrique des services

    function CommandeFontion(CommandeService) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des commandes

            CommandeService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.commandes = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log('error.data.message');
            }
        }

    }
    ;

    //============  Author dialog controller ====================////dialog fait l'ajout et la modification

    CommandeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CommandeService', 'ArtisanService', 'PanierService', 'ClientService', 'AdminService'];

    function CommandeDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, CommandeService, ArtisanService, PanierService, ClientService, AdminService) {

        var vm = this;

        vm.client = entity;
        vm.admin = entity;
        vm.panier = entity;
        vm.artisan = entity;
        vm.commande = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            console.log("mon non est ..." + vm.commande.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            //    vm.isSaving = true;
            if (vm.commande.id !== null) {
                console.log("vm.commande.id = " + vm.commande.id + " vm.Artisan.nom = " + vm.commande.artisan.nom + " vm.Panier.id = " + vm.commande.panier.id + " vm.Admin.nom = " + vm.commande.admin.nom + " vm.Client.id = " + vm.commande.client.id);//pour le débogga, on peut le rétirer
                CommandeService.update({id: vm.commande.id}, vm.commande, onSaveSuccess, onSaveError);
            } else {

                CommandeService.save(vm.commande, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('vitrine :commandeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }

        loadAll1();

        function loadAll1() {//Fontion qui affiche la liste des clients

            ClientService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                $scope.clients = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }


        loadAll2();

        function loadAll2() {//Fontion qui affiche la liste des admins

            AdminService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                $scope.admins = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }


        loadAll3();

        function loadAll3() {//Fontion qui affiche la liste des artisants

            ArtisanService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                $scope.artisans = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }
    }
    ;

    //============  Author delete controller ====================//

    CommandeDeleteController.$inject = ['$uibModalInstance', 'CommandeService', 'entity'];

    function   CommandeDeleteController($uibModalInstance, CommandeService, entity) {
        var vm = this;


        vm.commande = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete(id) {
            CommandeService.delete({id: id},
                    function () {
                        $uibModalInstance.close(true);
                    });
        }
    }

})();
