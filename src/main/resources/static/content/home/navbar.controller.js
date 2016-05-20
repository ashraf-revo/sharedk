'use strict';

angular.module('shared')
    .controller('NavbarController', function (ChatService, Principal, Auth, $state, $scope,$http) {
        //$scope.notification = [];
        //Principal.identity().then(function (user) {
        //    $scope.user = user;
        //});
        //$scope.logout = function () {
        //    Auth.logout();
        //    $state.go('login');
        //};
        //$scope.load = function () {
        //    var id = "";
        //    $scope.notification.forEach(function (d) {
        //        id = d.id;
        //    });
        //    $http.get('api/notification?id=' + id).success(function (notification) {
        //        notification.forEach(function (d) {
        //            $scope.notification.push(d);
        //        });
        //    });
        //};
        //
        //ChatService.Notifications().then(null, null, function (notification) {
        //    $scope.notification.splice(0, 0, notification);
        //    //$scope.notification.unshift(notification);
        //});
        //$scope.load();
    });