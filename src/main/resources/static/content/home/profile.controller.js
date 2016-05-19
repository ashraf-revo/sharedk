'use strict';

angular.module('shared')
    .controller('ProfileController', function (ChatService) {
        ChatService.Images().then(null, null, function (message) {
        });
    });