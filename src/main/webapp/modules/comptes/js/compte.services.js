
(function() {
    'use strict';
    angular
        .module('vitrine')
        .factory('CompteService', CompteFonction);

    CompteFonction.$inject = ['$resource'];

    function CompteFonction ($resource) {
        var resourceUrl =  'api/comptes/:id';

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
