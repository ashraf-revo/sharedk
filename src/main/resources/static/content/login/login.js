angular.module("shared").config(function ($stateProvider) {
    $stateProvider.state("login", {
        parent: "site",
        url: "/login",
        views:{
            "content@":{
                templateUrl:"content/login/login.html",
                controller:'LoginController'
            }
        },
        data: {
            roles: [],
            pageTitle: 'Login'
        }

    })
});