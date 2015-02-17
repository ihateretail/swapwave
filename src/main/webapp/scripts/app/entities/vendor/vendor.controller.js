'use strict';

angular.module('swapwaveApp')
    .controller('VendorController', function ($scope, Vendor) {
        $scope.vendors = [];
        $scope.loadAll = function() {
            Vendor.query(function(result) {
               $scope.vendors = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Vendor.save($scope.vendor,
                function () {
                    $scope.loadAll();
                    $('#saveVendorModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.vendor = Vendor.get({id: id});
            $('#saveVendorModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.vendor = Vendor.get({id: id});
            $('#deleteVendorConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Vendor.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteVendorConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.vendor = {name: null, dba: null, federalId: null, yearEstablished: null, ownershipType: null, ownershipStates: null, businessType: null, location: null, numberofLocations: null, yearsInPresentLoc: null, jbtId: null, jbtRating: null, bbbrating: null, id: null};
        };
    });
