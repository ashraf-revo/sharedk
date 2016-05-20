'use strict';

angular.module('shared')
    .controller('NavbarController', function (ChatService, Principal, Auth, $state,$scope) {
        Principal.identity().then(function (response) {
            $scope.user = response;
        });
        $scope.logout = function () {
            Auth.logout();
            $state.go('login');
        };

        ChatService.Notifications().then(null, null, function (notification) {
        });
    });