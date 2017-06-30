(function () {
    'use strict';

    angular
            .module('vitrine')
            .controller('CommentaireController', CommentaireFontion)
            .controller('CommentaireDialogController', CommentaireDialogController)
            .controller('CommentaireDeleteController', CommentaireDeleteController);


    //============  Generale Author Controller  ======================//

    CommentaireFontion.$inject = ['CommentaireService'];//CommentaireService est la fabrique des services

    function CommentaireFontion(CommentaireService) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des commentaires

            CommentaireService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.commentaires = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log('error.data.message');
            }
        }

    }
    ;

    //============  Author dialog controller ====================////dialog fait l'ajout et la modification

    CommentaireDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CommentaireService', 'ProduitService'];

    function CommentaireDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, CommentaireService, ProduitService) {

        var vm = this;

        vm.produit = entity;
        vm.commentaire = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            console.log("mon non est ..." + vm.commentaire.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            //    vm.isSaving = true;
            if (vm.commentaire.id !== null) {
                console.log("vm.commentaire.id = " + vm.commentaire.id + " vm.Produit.nom = " + vm.commentaire.produit.nomProduit);//pour le débogga, on peut le rétirer
                CommentaireService.update({id: vm.commentaire.id}, vm.commentaire, onSaveSuccess, onSaveError);
            } else {

                CommentaireService.save(vm.commentaire, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('vitrine :commentaireUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }

        loadAll();

        function loadAll() {//Fontion qui affiche la liste des catArts

            ProduitService.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                $scope.produits = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                console.log(error.data.message);
            }
        }
    }
    ;

    //============  Author delete controller ====================//

    CommentaireDeleteController.$inject = ['$uibModalInstance', 'CommentaireService', 'entity'];

    function   CommentaireDeleteController($uibModalInstance, CommentaireService, entity) {
        var vm = this;

        vm.commentaire = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete(id) {
            CommentaireService.delete({id: id},
                    function () {
                        $uibModalInstance.close(true);
                    });
        }
    }

})();
