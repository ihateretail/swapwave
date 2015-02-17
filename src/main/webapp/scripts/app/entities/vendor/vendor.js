'use strict';

angular.module('swapwaveApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('vendor', {
                parent: 'entity',
                url: '/vendor',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/vendor/vendors.html',
                        controller: 'VendorController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('vendor');
                        return $translate.refresh();
                    }]
                }
            })
            .state('vendorDetail', {
                parent: 'entity',
                url: '/vendor/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/vendor/vendor-detail.html',
                        controller: 'VendorDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('vendor');
                        return $translate.refresh();
                    }]
                }
            });
    });
