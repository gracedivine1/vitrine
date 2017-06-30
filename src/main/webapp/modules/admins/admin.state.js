(function () {
    'use strict';

    angular
            .module('vitrine')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('admin', {
//                    parent: 'module',
                    url: '/admins',
                    views: {
                        'moduleContent': {
                            templateUrl: 'modules/admins/views/admins.list.html',
                            controller: 'AdminController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('admin.detail', {
                    parent: 'admin',
                    url: '/admin/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/admins/views/admin.detail.html',
                                controller: 'AdminDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['AdminService', function (AdminService) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return AdminService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('admin', null, {reload: true});
                                    }, function () {
                                        $state.go('admin');
                                    });
                        }]
                })
                .state('admin.new', {
//                    parent: 'admin',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/admins/views/admin.create.html',
                                controller: 'AdminDialogController',
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
                                $state.go('admin', null, {reload: true});
                            }, function () {
                                $state.go('admin');
                            });
                        }]
                })
                .state('admin.edit', {
//                    parent: 'admin',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/admins/views/admin.edit.html',
                                controller: 'AdminDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: ['AdminService', function (AdminService) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return AdminService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('admin', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('admin.delete', {
//                    parent: 'admin',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/admins/views/admin.delete.html',
                                controller: 'AdminDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                  entity: ['AdminService', function (AdminService) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return AdminService.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('admin', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
            }

})();
