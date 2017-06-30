
(function() {
    'use strict';
    angular
        .module('vitrine')
        .factory('CatProduitService', CatProduitFonction);

    CatProduitFonction.$inject = ['$resource'];

    function CatProduitFonction ($resource) {
        var resourceUrl =  'api/catProduits/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                     console.log(data);
                    return angular.toJson(data);
                }
            }
        });
    }
})();
