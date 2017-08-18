(function () {
    'use strict';

    angular
            .module('vitrine')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('artisan', {
//                    parent: 'module',
                    url: '/artisans',
                    views: {
                        'moduleContent': {
                            templateUrl: 'modules/artisans/views/artisans.list.html',
                            controller: 'ArtisanController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('artisan.detail', {
                    parent: 'artisan',
                    url: '/artisan/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/artisans/views/artisan.detail.html',
                                controller: 'ArtisanDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['ArtisanService', function (ArtisanService) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return ArtisanService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('artisan', null, {reload: true});
                                    }, function () {
                                        $state.go('artisan');
                                    });
                        }]
                })
                .state('artisan.new', {
//                    parent: 'artisan',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/artisans/views/artisan.create.html',
                                controller: 'ArtisanDialogController',
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
                                            sousCatArt: null,
                                            sexe: null,
                                            image: null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('artisan', null, {reload: true});
                            }, function () {
                                $state.go('artisan');
                            });
                        }]
                })
                .state('artisan.edit', {
//                    parent: 'artisan',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/artisans/views/artisan.edit.html',
                                controller: 'ArtisanDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: ['ArtisanService', function (ArtisanService) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return ArtisanService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('artisan', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('artisan.delete', {
//                    parent: 'artisan',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/artisans/views/artisan.delete.html',
                                controller: 'ArtisanDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                  entity: ['ArtisanService', function (ArtisanService) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return ArtisanService.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('artisan', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
            }

})();
