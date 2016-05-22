angular.module("shared").config(function ($stateProvider) {
    $stateProvider.state("register", {
        parent: "site",
        url: "/register",
        views: {
            "content@": {
                templateUrl: "content/register/register.html",
                controller: "RegisterController"
            }
        },
        data: {
            roles: [],
            pageTitle: 'Register'
        }

    });
});