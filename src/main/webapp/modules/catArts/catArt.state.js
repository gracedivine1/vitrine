(function () {
    'use strict';

    angular
            .module('vitrine')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('catArt', {
//                    parent: 'module',
                    url: '/catArts',
                    views: {
                        'moduleContent': {
                            templateUrl: 'modules/catArts/views/catArts.list.html',
                            controller: 'CatArtController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('catArt.detail', {
//                    parent: 'catArt',
                    url: '/catArt/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/catArts/views/catArt.detail.html',
                                controller: 'CatArtDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['CatArtService', function (CatArtService) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return CatArtService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('catArt', null, {reload: true});
                                    }, function () {
                                        $state.go('catArt');
                                    });
                        }]
                })
                .state('catArt.new', {
//                    parent: 'catArt',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/catArts/views/catArt.create.html',
                                controller: 'CatArtDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: function () {
                                        return {
                                            id: null,
                                            nomCatArt: null

                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('catArt', null, {reload: true});
                            }, function () {
                                $state.go('catArt');
                            });
                        }]
                })
                .state('catArt.edit', {
//                    parent: 'catArt',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/catArts/views/catArt.edit.html',
                                controller: 'CatArtDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: ['CatArtService', function (CatArtService) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return CatArtService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('catArt', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('catArt.delete', {
//                    parent: 'catArt',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/catArts/views/catArt.delete.html',
                                controller: 'CatArtDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                  entity: ['CatArtService', function (CatArtService) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return CatArtService.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('catArt', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
            }

})();
