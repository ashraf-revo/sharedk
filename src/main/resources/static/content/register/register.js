angular.module("shared").config(function ($stateProvider) {
    $stateProvider.state("register", {
        parent:"site",
        url:"/register",
        views:{
            "content@":{
                templateUrl:"content/register/register.html"
            }
        },
        data: {
            roles: [],
            pageTitle: 'Register'
        }

    });
});