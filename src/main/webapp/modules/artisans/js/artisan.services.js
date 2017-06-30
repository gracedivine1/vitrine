
(function() {
    'use strict';
    angular
        .module('vitrine')
        .factory('ArtisanService', ArtisanFonction);

    ArtisanFonction.$inject = ['$resource'];

    function ArtisanFonction ($resource) {
        var resourceUrl =  'api/artisans/:id';

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
