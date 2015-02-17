'use strict';

angular.module('swapwaveApp')
    .factory('Vendor', function ($resource) {
        return $resource('api/vendors/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.yearEstablished = new Date(data.yearEstablished);
                    return data;
                }
            }
        });
    });
