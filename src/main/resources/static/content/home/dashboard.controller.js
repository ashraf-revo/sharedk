'use strict';

angular.module('shared')
    .controller('DashboardController', function (ChatService, $scope) {
        ChatService.Images().then(null, null, function (message) {
        });
        $scope.share = function () {
            console.log($scope.text);
            console.log($scope.file[0].base64);
        }
    });