(function () {
    'use strict';

    angular
            .module('vitrine')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
                .state('panier', {
//                    parent: 'module',
                    url: '/paniers',
                    views: {
                        'moduleContent': {
                            templateUrl: 'modules/paniers/views/paniers.list.html',
                            controller: 'PanierController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('panier.detail', {
                    parent: 'panier',
                    url: '/panier/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/paniers/views/panier.detail.html',
                                controller: 'PanierDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['PanierService', function (PanierService) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return PanierService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('panier', null, {reload: true});
                                    }, function () {
                                        $state.go('panier');
                                    });
                        }]
                })
                .state('panier.new', {
//                    parent: 'panier',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/paniers/views/panier.create.html',
                                controller: 'PanierDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: function () {
                                        return {
                                            id: null,
                                            prixUnitaire: null,
                                            commande: null,
                                            produit: null,                                            
                                            quantite: null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('panier', null, {reload: true});
                            }, function () {
                                $state.go('panier');
                            });
                        }]
                })
                .state('panier.edit', {
//                    parent: 'panier',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/paniers/views/panier.edit.html',
                                controller: 'PanierDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: ['PanierService', function (PanierService) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return PanierService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('panier', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('panier.delete', {
//                    parent: 'panier',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/paniers/views/panier.delete.html',
                                controller: 'PanierDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                    entity: ['PanierService', function (PanierService) {
                                            console.log("valeur de id peut etre ..." + $stateParams.id);
                                            return PanierService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('panier', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
    }

})();
