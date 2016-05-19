angular.module("shared").config(function ($stateProvider) {
    $stateProvider.state("dashboard", {
        parent: "home",
        url: "/",
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Dashboard'
        },
        views: {
            "content@": {
                templateUrl: "content/home/home.html",
                controller:"DashboardController"
            }
        }
    })
});