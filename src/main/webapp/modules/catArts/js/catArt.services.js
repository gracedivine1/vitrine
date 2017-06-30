
(function() {
    'use strict';
    angular
        .module('vitrine')
        .factory('CatArtService', CatArtFonction);

    CatArtFonction.$inject = ['$resource'];

    function CatArtFonction ($resource) {
        var resourceUrl =  'api/catArts/:id';

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
                }
            }
        });
    }
})();
