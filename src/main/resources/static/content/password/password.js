angular.module("shared").config(function ($stateProvider) {
    $stateProvider.state("password", {
        parent: "site",
        url: "/password",
        views: {
            "content@": {
                templateUrl: "content/password/password.html"
            }
        },
        data: {
            roles: [],
            pageTitle: 'Password'
        }

    });
});