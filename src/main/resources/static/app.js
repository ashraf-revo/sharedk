angular.module('shared', ['LocalStorageModule', 'naif.base64',
        'ngResource', 'ngCacheBuster', 'ui.router'])
    .run(function ($rootScope, $location, $window, $http, $state, Auth, Principal) {
        $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
            $rootScope.toState = toState;
            $rootScope.toStateParams = toStateParams;

            if (Principal.isIdentityResolved()) {
                Auth.authorize();
            }
        });


        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            $rootScope.previousStateName = fromState.name;
            $rootScope.previousStateParams = fromParams;
            if (toState.data.pageTitle) {
                titleKey = toState.data.pageTitle;
                $window.document.title = titleKey;
            }
        });


    })

    .config(function ($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider, httpRequestInterceptorCacheBusterProvider) {
        httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/], true);

        $urlRouterProvider.otherwise('/login');
        $stateProvider.state('site', {
            'abstract': true,
            resolve: {
                authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ]

            }
        });
        $httpProvider.interceptors.push('authExpiredInterceptor');
        $httpProvider.interceptors.push('authInterceptor');

    })
    .config(['$urlMatcherFactoryProvider', function ($urlMatcherFactory) {
        $urlMatcherFactory.type('boolean', {
            name: 'boolean',
            decode: function (val) {
                return val == true ? true : val == "true" ? true : false
            },
            encode: function (val) {
                return val ? 1 : 0;
            },
            equals: function (a, b) {
                return this.is(a) && a === b;
            },
            is: function (val) {
                return [true, false, 0, 1].indexOf(val) >= 0
            },
            pattern: /bool|true|0|1/
        });
    }]);