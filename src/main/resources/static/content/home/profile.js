'use strict';
angular.module("shared").config(function ($stateProvider) {
    $stateProvider.state("profile", {
        parent: "home",
        url: "/profile",
        views: {
            "content@": {
                templateUrl: "content/home/home.html"
            },
            "profile@profile": {
                templateUrl: "content/home/profile.html",
                controller: "ProfileController"
            }
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Profile'
        }

    })
});