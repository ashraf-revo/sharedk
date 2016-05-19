'use strict';

angular.module('shared')
    .factory('Account', function Account($resource) {
        return $resource('api/user', {}, {
            'get': { method: 'GET', params: {}, isArray: false,
                interceptor: {
                    response: function(response) {
                        // expose response
                        return response;
                    }
                }
            }
        });
    });
