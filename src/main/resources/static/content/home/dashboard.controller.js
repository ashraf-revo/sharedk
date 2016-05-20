'use strict';

angular.module('shared')
    .controller('DashboardController', function (ChatService, $scope, $http) {
        $scope.image = [];
        $scope.idNotExist = function (image) {
            var found = true;
            for (var i = 0; i < $scope.image; i++) {
                if (image.id == $scope.image[i].id) {
                    found = false;
                }
            }
            return found;
        };

        ChatService.Images().then(null, null, function (image) {
            if ($scope.idNotExist(image))
                $scope.image.splice(0, 0, image);
        });

        $scope.load = function () {
            var id = "";
            $scope.image.forEach(function (d) {
                id = d.id;
            });
            $http.get('api/image?id=' + id).success(function (image) {
                image.forEach(function (d) {
                    $scope.image.push(d);
                });
            });
        };

        $scope.share = function () {
            if ($scope.file != null && $scope.file.length > 0) {
                $http.post('api/image', {
                    "file": $scope.file[0].base64,
                    "info": $scope.text
                }).success(function (image) {
                    $scope.image.splice(0, 0, image);
                });
            }
            $scope.file = null;
            $scope.info = "";
        };
        $scope.load();
    });