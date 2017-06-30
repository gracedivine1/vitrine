(function () {
    'use strict';

    angular
            .module('vitrine')
            .controller('CatArtController', CatArtFontion)
            .controller('CatArtDialogController', CatArtDialogController)
            .controller('CatArtDeleteController', CatArtDeleteController);


    //============  Generale Author Controller  ======================//

    CatArtFontion.$inject = ['CatArtService'];//CatArtService est la fabrique des services

    function CatArtFontion(CatArtService) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des catArts

            CatArtService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.catArts = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }

    }
    ;

    //============  Author dialog controller ====================////dialog fait l'ajout et la modification

    CatArtDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CatArtService'];

    function CatArtDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, CatArtService) {

        var vm = this;

        vm.catArt = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            console.log("mon non est ..." + vm.catArt.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            //    vm.isSaving = true;
            if (vm.catArt.id !== null) {
                console.log("vm.catArt.id = " + vm.catArt.id + " vm.catArt.nom = " + vm.catArt.nom);//pour le débogga, on peut le rétirer
                CatArtService.update({id: vm.catArt.id}, vm.catArt, onSaveSuccess, onSaveError);
            } else {
                CatArtService.save(vm.catArt, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('vitrine :catArtUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }
    }
    ;

    //============  Author delete controller ====================//

    CatArtDeleteController.$inject = ['$uibModalInstance', 'CatArtService', 'entity'];

    function   CatArtDeleteController($uibModalInstance, CatArtService, entity) {
        var vm = this;

        vm.catArt = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete(id) {
            CatArtService.delete({id: id},
                    function () {
                        $uibModalInstance.close(true);
                    });
        }
    }

})();
