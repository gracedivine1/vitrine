(function () {
    'use strict';

    angular
            .module('vitrine')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('compte', {
//                    parent: 'module',
                    url: '/comptes',
                    views: {
                        'moduleContent': {
                            templateUrl: 'modules/comptes/views/comptes.list.html',
                            controller: 'CompteController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('compte.detail', {
                    parent: 'compte',
                    url: '/compte/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/comptes/views/compte.detail.html',
                                controller: 'CompteDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['CompteService', function (CompteService) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return CompteService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('compte', null, {reload: true});
                                    }, function () {
                                        $state.go('compte');
                                    });
                        }]
                })
                .state('compte.new', {
//                    parent: 'compte',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/comptes/views/compte.create.html',
                                controller: 'CompteDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: function () {
                                        return {
                                            id: null,
                                            typeCompte: null

                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('compte', null, {reload: true});
                            }, function () {
                                $state.go('compte');
                            });
                        }]
                })
                .state('compte.edit', {
//                    parent: 'compte',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/comptes/views/compte.edit.html',
                                controller: 'CompteDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: ['CompteService', function (CompteService) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return CompteService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('compte', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('compte.delete', {
//                    parent: 'compte',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/comptes/views/compte.delete.html',
                                controller: 'CompteDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                  entity: ['CompteService', function (CompteService) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return CompteService.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('compte', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
            }

})();
