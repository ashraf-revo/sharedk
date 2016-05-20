'use strict';
angular.module("shared").config(function ($stateProvider) {
    $stateProvider.state("profile", {
        parent: "home",
        url: "/profile",
        views: {
            "content@": {
                templateUrl: "content/home/dashboard.html"
            },
            "profile@profile": {
                templateUrl: "content/home/profile.html",
                controller: "ProfileController"
            },
            "navbar@profile": {
                templateUrl: "content/home/navbar.html",
                controller: "NavbarController"
            }
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Profile'
        }

    })
});