(function () {
    'use strict';

    angular
            .module('vitrine')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('produit', {
//                    parent: 'module',
                    url: '/produits',
                    views: {
                        'moduleContent': {
                            templateUrl: 'modules/produits/views/produits.list.html',
                            controller: 'ProduitController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('produit.detail', {
                    parent: 'produit',
                    url: '/produit/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/produits/views/produit.detail.html',
                                controller: 'ProduitDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['ProduitService', function (ProduitService) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return ProduitService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('produit', null, {reload: true});
                                    }, function () {
                                        $state.go('produit');
                                    });
                        }]
                })
                .state('produit.new', {
//                    parent: 'produit',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/produits/views/produit.create.html',
                                controller: 'ProduitDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: function () {
                                        return {
                                            id: null,
                                            nomProduit: null,
                                            prix: null,
                                            imageURL: null,
                                            description: null,
                                            catProduit: null,
                                            datePublication: null

                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('produit', null, {reload: true});
                            }, function () {
                                $state.go('produit');
                            });
                        }]
                })
                .state('produit.edit', {
//                    parent: 'produit',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/produits/views/produit.edit.html',
                                controller: 'ProduitDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: ['ProduitService', function (ProduitService) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return ProduitService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('produit', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('produit.delete', {
//                    parent: 'produit',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/produits/views/produit.delete.html',
                                controller: 'ProduitDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                  entity: ['ProduitService', function (ProduitService) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return ProduitService.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('produit', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
            }

})();
