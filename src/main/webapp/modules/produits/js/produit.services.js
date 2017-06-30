
(function() {
    'use strict';
    angular
        .module('vitrine')
        .factory('ProduitService', ProduitFonction);

    ProduitFonction.$inject = ['$resource'];

    function ProduitFonction ($resource) {
        var resourceUrl =  'api/produits/:id';

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
                    return angular.toJson(data);
                   // console.log(data);
                }
            }
        });
    }
})();
