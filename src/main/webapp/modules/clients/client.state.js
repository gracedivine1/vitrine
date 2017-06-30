(function () {
    'use strict';

    angular
            .module('vitrine')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('client', {
//                    parent: 'module',
                    url: '/clients',
                    views: {
                        'moduleContent': {
                            templateUrl: 'modules/clients/views/clients.list.html',
                            controller: 'CommandeController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('client.detail', {
                    parent: 'client',
                    url: '/client/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/clients/views/client.detail.html',
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
                                        $state.go('client', null, {reload: true});
                                    }, function () {
                                        $state.go('client');
                                    });
                        }]
                })
                .state('client.new', {
//                    parent: 'client',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/clients/views/client.create.html',
                                controller: 'CommandeDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: function () {
                                        return {
                                            id: null,
                                            nom: null,
                                            prenom: null,
                                            login: null,
                                            motpasse: null,
                                            numTel: null,
                                            email: null,
                                            ville: null,
                                            image: null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('client', null, {reload: true});
                            }, function () {
                                $state.go('client');
                            });
                        }]
                })
                .state('client.edit', {
//                    parent: 'client',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/clients/views/client.edit.html',
                                controller: 'ClientDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: ['ClientService', function (ClientService) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return ClientService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('client', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('client.delete', {
//                    parent: 'client',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/clients/views/client.delete.html',
                                controller: 'ClientDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                  entity: ['ClientService', function (ClientService) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return ClientService.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('client', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
            }

})();
