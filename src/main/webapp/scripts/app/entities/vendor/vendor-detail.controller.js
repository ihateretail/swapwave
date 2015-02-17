'use strict';

angular.module('swapwaveApp')
    .controller('VendorDetailController', function ($scope, $stateParams, Vendor) {
        $scope.vendor = {};
        $scope.load = function (id) {
            Vendor.get({id: id}, function(result) {
              $scope.vendor = result;
            });
        };
        $scope.load($stateParams.id);
    });
