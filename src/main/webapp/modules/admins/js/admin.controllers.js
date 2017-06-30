(function () {
    'use strict';

    angular
            .module('vitrine')
            .controller('AdminController', AdminFontion)
            .controller('AdminDialogController', AdminDialogController)
            .controller('AdminDeleteController', AdminDeleteController);


    //============  Generale Author Controller  ======================//

    AdminFontion.$inject = ['AdminService'];//AdminService est la fabrique des services

    function AdminFontion(AdminService) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des admins

            AdminService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.admins = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }

    }
    ;

    //============  Author dialog controller ====================////dialog fait l'ajout et la modification

    AdminDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'AdminService'];

    function AdminDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, AdminService) {

        var vm = this;

        vm.admin = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            console.log("mon non est ..." + vm.admin.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            //    vm.isSaving = true;
            if (vm.admin.id !== null) {
                console.log("vm.admin.id = " + vm.admin.id + " vm.admin.nom = " + vm.admin.nom);//pour le débogga, on peut le rétirer
                AdminService.update({id: vm.admin.id}, vm.admin, onSaveSuccess, onSaveError);
            } else {
                AdminService.save(vm.admin, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('vitrine :adminUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }
    }
    ;

    //============  Author delete controller ====================//

    AdminDeleteController.$inject = ['$uibModalInstance', 'AdminService', 'entity'];

    function   AdminDeleteController($uibModalInstance, AdminService, entity) {
        var vm = this;

        vm.admin = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete(id) {
            AdminService.delete({id: id},
                    function () {
                        $uibModalInstance.close(true);
                    });
        }
    }

})();
