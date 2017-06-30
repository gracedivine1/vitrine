(function () {
    'use strict';

    angular
            .module('vitrine')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('commande', {
//                    parent: 'module',
                    url: '/commandes',
                    views: {
                        'moduleContent': {
                            templateUrl: 'modules/commandes/views/commandes.list.html',
                            controller: 'CommandeController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('commande.detail', {
                    parent: 'commande',
                    url: '/commande/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/commandes/views/commande.detail.html',
                                controller: 'CommandeDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['CommandeService', function (CommandeService) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return CommandeService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('commande', null, {reload: true});
                                    }, function () {
                                        $state.go('commande');
                                    });
                        }]
                })
                .state('commande.new', {
//                    parent: 'commande',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/commandes/views/commande.create.html',
                                controller: 'CommandeDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: function () {
                                        return {
                                            id: null,
                                            statut: null,
                                            date: null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('commande', null, {reload: true});
                            }, function () {
                                $state.go('commande');
                            });
                        }]
                })
                .state('commande.edit', {
//                    parent: 'commande',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/commandes/views/commande.edit.html',
                                controller: 'CommandeDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: ['CommandeService', function (CommandeService) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return CommandeService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('commande', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('commande.delete', {
//                    parent: 'commande',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/commandes/views/commande.delete.html',
                                controller: 'CommandeDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                  entity: ['CommandeService', function (CommandeService) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return CommandeService.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('commande', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
            }

})();
