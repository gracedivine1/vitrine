(function () {
    'use strict';

    angular
            .module('vitrine')
            .controller('ArtisanController', ArtisanFontion)
            .controller('ArtisanDialogController', ArtisanDialogController)
            .controller('ArtisanDeleteController', ArtisanDeleteController);


    //============  Generale Author Controller  ======================//

    ArtisanFontion.$inject = ['ArtisanService'];//ArtisanService est la fabrique des services

    function ArtisanFontion(ArtisanService) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des artisans

            ArtisanService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.artisans = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }

    }
    ;

    //============  Author dialog controller ====================////dialog fait l'ajout et la modification

    ArtisanDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ArtisanService', 'SousCatArtService'];

    function ArtisanDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, ArtisanService, SousCatArtService) {

        var vm = this;

        vm.sousCatArt = entity;
        vm.artisan = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            console.log("mon non est ..." + vm.artisan.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            //    vm.isSaving = true;
            if (vm.artisan.id !== null) {
                console.log("vm.artisan.id = " + vm.artisan.id + " vm.sousCatArt.nom = " + vm.artisan.sousCatArt.nomSousCatArt);//pour le débogga, on peut le rétirer
                ArtisanService.update({id: vm.artisan.id}, vm.artisan, onSaveSuccess, onSaveError);
            } else {
                ArtisanService.save(vm.artisan, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('vitrine :artisanUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }


        loadAll();

        function loadAll() {//Fontion qui affiche la liste des sousCatArts

            SousCatArtService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                $scope.sousCatArts = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }
    }
    ;

    //============  Author delete controller ====================//

    ArtisanDeleteController.$inject = ['$uibModalInstance', 'ArtisanService', 'entity'];

    function   ArtisanDeleteController($uibModalInstance, ArtisanService, entity) {
        var vm = this;

        vm.artisan = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete(id) {
            ArtisanService.delete({id: id},
                    function () {
                        $uibModalInstance.close(true);
                    });
        }
    }

})();
