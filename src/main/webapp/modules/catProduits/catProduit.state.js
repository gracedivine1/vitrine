(function () {
    'use strict';

    angular
            .module('vitrine')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('catProduit', {
//                    parent: 'module',
                    url: '/catProduits',
                    views: {
                        'moduleContent': {
                            templateUrl: 'modules/catProduits/views/catProduits.list.html',
                            controller: 'CatProduitController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('catProduit.detail', {
                    parent: 'catProduit',
                    url: '/catProduit/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/catProduits/views/catProduit.detail.html',
                                controller: 'CatProduitDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['CatProduitService', function (CatProduitService) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return CatProduitService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('catProduit', null, {reload: true});
                                    }, function () {
                                        $state.go('catProduit');
                                    });
                        }]
                })
                .state('catProduit.new', {
//                    parent: 'catProduit',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/catProduits/views/catProduit.create.html',
                                controller: 'CatProduitDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: function () {
                                        return {
                                            id: null,
                                            nomCatProduit: null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('catProduit', null, {reload: true});
                            }, function () {
                                $state.go('catProduit');
                            });
                        }]
                })
                .state('catProduit.edit', {
//                    parent: 'catProduit',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/catProduits/views/catProduit.edit.html',
                                controller: 'CatProduitDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: ['CatProduitService', function (CatProduitService) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return CatProduitService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('catProduit', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('catProduit.delete', {
//                    parent: 'catProduit',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/catProduits/views/catProduit.delete.html',
                                controller: 'CatProduitDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                  entity: ['CatProduitService', function (CatProduitService) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return CatProduitService.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('catProduit', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
            }

})();
