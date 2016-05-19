'use strict';

angular.module('shared')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


