(function () {
    'use strict';

    angular
            .module('vitrine')
            .controller('SousCatArtController', SousCatArtFontion)
            .controller('SousCatArtDialogController', SousCatArtDialogController)
            .controller('SousCatArtDeleteController', SousCatArtDeleteController);


    //============  Generale Author Controller  ======================//

    SousCatArtFontion.$inject = ['SousCatArtService'];//SousCatArtService est la fabrique des services

    function SousCatArtFontion(SousCatArtService) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des sousCatArts

            SousCatArtService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.sousCatArts = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log('error.data.message');
            }
        }

    }
    ;

    //============  Author dialog controller ====================////dialog fait l'ajout et la modification

    SousCatArtDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'SousCatArtService','CatArtService'];

    function SousCatArtDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, SousCatArtService,CatArtService) {

        var vm = this;

        vm.sousCatArt = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            console.log("mon non est ..." + vm.sousCatArt.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            //    vm.isSaving = true;
            if (vm.sousCatArt.id !== null) {
                console.log("vm.sousCatArt.id = " + vm.sousCatArt.id + " vm.CatArt.nom = " + vm.sousCatArt.catArt.nomCatArt);//pour le débogga, on peut le rétirer
                SousCatArtService.update({id: vm.sousCatArt.id}, vm.sousCatArt, onSaveSuccess, onSaveError);
            } else {
                
                SousCatArtService.save(vm.sousCatArt, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('vitrine :sousCatArtUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des catArts

            CatArtService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                $scope.catArts = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }
    }
    ;

    //============  Author delete controller ====================//

    SousCatArtDeleteController.$inject = ['$uibModalInstance', 'SousCatArtService', 'entity'];

    function   SousCatArtDeleteController($uibModalInstance, SousCatArtService, entity) {
        var vm = this;

        vm.sousCatArt = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete(id) {
            SousCatArtService.delete({id: id},
                    function () {
                        $uibModalInstance.close(true);
                    });
        }
    }

})();
