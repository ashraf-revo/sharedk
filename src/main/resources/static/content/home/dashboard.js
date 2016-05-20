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
                templateUrl: "content/home/dashboard.html",
                controller: "DashboardController"
            },
            "navbar@dashboard": {
                templateUrl: "content/home/navbar.html",
                controller: "NavbarController"
            }

        }
    })
});