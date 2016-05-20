'use strict';

angular.module('shared')
    .controller('DashboardController', function (ChatService, $scope, $http) {

        $scope.image = [];
        $scope.id = "";
        ChatService.Images().then(null, null, function (message) {

        });

        $scope.load = function () {
            $http.get('api/image?id=' + $scope.id).success(function (response) {
                response.forEach(function (d) {
                    $scope.id = d.id;
                    $scope.image.push(d);
                });
            });
        };

        $scope.share = function () {
            if ($scope.file != null && $scope.file.length > 0) {
                $http.post('api/image', {
                    "file": $scope.file[0].base64,
                    "info": $scope.text
                }).success(function (response) {
                    $scope.image.splice(0, 0, response);
                });
            }
            $scope.file = null;
            $scope.info = "";
        };
        $scope.load();
    });