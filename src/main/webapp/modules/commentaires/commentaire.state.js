(function () {
    'use strict';

    angular
            .module('vitrine')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('commentaire', {
//                    parent: 'module',
                    url: '/commentaires',
                    views: {
                        'moduleContent': {
                            templateUrl: 'modules/commentaires/views/commentaires.list.html',
                            controller: 'CommentaireController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('commentaire.detail', {
                    parent: 'commentaire',
                    url: '/commentaire/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/commentaires/views/commentaire.detail.html',
                                controller: 'CommentaireDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['CommentaireService', function (CommentaireService) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return CommentaireService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('commentaire', null, {reload: true});
                                    }, function () {
                                        $state.go('commentaire');
                                    });
                        }]
                })
                .state('commentaire.new', {
//                    parent: 'commentaire',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/commentaires/views/commentaire.create.html',
                                controller: 'CommentaireDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: function () {
                                        return {
                                            id: null,
                                            commCom: null

                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('commentaire', null, {reload: true});
                            }, function () {
                                $state.go('commentaire');
                            });
                        }]
                })
                .state('commentaire.edit', {
//                    parent: 'commentaire',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/commentaires/views/commentaire.edit.html',
                                controller: 'CommentaireDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: ['CommentaireService', function (CommentaireService) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return CommentaireService.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('commentaire', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('commentaire.delete', {
//                    parent: 'commentaire',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/commentaires/views/commentaire.delete.html',
                                controller: 'CommentaireDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                  entity: ['CommentaireService', function (CommentaireService) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return CommentaireService.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('commentaire', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
            }

})();
