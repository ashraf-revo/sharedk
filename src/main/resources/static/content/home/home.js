angular.module("shared").config(function ($stateProvider) {
    $stateProvider.state("home", {
        parent: "site",
        'abstract': true
    })
});