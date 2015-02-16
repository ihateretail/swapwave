'use strict';

angular.module('swapwaveApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


