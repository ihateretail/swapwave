'use strict';

angular.module('swapwaveApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
