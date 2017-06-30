(function () {
    'use strict';

    angular
            .module('vitrine')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
                .state('sousCatArt', {
//                    parent: 'module',
                    url: '/sousCatArts',
                    views: {
                        'moduleContent': {
                            templateUrl: 'modules/sousCatArts/views/sousCatArts.list.html',
                            controller: 'SousCatArtController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('sousCatArt.detail', {
                    parent: 'sousCatArt',
                    url: '/sousCatArt/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/sousCatArts/views/sousCatArt.detail.html',
                                controller: 'SousCatArtDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['SousCatArtService', function (SousCatArtService) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return SousCatArtService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('sousCatArt', null, {reload: true});
                                    }, function () {
                                        $state.go('sousCatArt');
                                    });
                        }]
                })
                .state('sousCatArt.new', {
//                    parent: 'sousCatArt',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/sousCatArts/views/sousCatArt.create.html',
                                controller: 'SousCatArtDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: function () {
                                        return {
                                            id: null,
                                            nomSousCatArt: null,
                                            cathArt: null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('sousCatArt', null, {reload: true});
                            }, function () {
                                $state.go('sousCatArt');
                            });
                        }]
                })
                .state('sousCatArt.edit', {
//                    parent: 'sousCatArt',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/sousCatArts/views/sousCatArt.edit.html',
                                controller: 'SousCatArtDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: ['SousCatArtService', function (SousCatArtService) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return SousCatArtService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('sousCatArt', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('sousCatArt.delete', {
//                    parent: 'sousCatArt',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/sousCatArts/views/sousCatArt.delete.html',
                                controller: 'SousCatArtDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                    entity: ['SousCatArtService', function (SousCatArtService) {
                                            console.log("valeur de id peut etre ..." + $stateParams.id);
                                            return SousCatArtService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('sousCatArt', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
    }

})();
