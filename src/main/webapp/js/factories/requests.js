'use strict';

app.factory('TaskRequest', ['$resource',
    function($resource) {
        return $resource('/rest/tasks/:id',
            {id: '@id'},
            {
                query: {
                    isArray: true,
                    method: 'GET',
                    params: {},
                    transformResponse: function(data) {
                        return angular.fromJson(data).body.rows
                    }
                },
                get: {method: 'GET', params: {id: '@id'}},
                save: {method: 'POST'},
                update: {method: 'PUT', params: {id: '@id'}},
                delete: {method: 'DELETE', params: {}}
            });
    }]);

app.factory('TaskFilter', ['$resource',
    function($resource) {
        return $resource('/rest/filter-tasks/:filter/:start/:size',
            {filter: '@filter'},
            {
                getTasks: {
                    isArray: true,
                    method: 'GET',
                    params: {filter: '@filter'}
                },
                getPage: {
                    isArray: true,
                    method: 'GET',
                    params: {filter: '@filter', start: '@start', size: '@size'}
                }
            }
        );
    }]);


