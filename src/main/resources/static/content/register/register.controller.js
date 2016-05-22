'use strict';

angular.module('shared')
    .controller('RegisterController', function ($http, $scope) {

        $scope.register = function () {
            $http.post("api/register", $scope.user).success(function (data) {

            });

        }
    });