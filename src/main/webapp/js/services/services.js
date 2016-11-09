'use strict';

app.service('TaskService', [
    '$http',
    function($http) {
        // synch
        this.getTasksByUrl = function(// page, limit,
                                      url) {
            return $http.get(url)
                .success(function(data) {
                    return data;
                })
                .error(function(err) {
                    return err;
                });
        };
        this.postRequest = function(url, data, callback) {
            return $http({
                url: url,
                method: "POST",
                data: data,
                headers: {'Content-Type': 'application/json'}
            }).success(function(data, status, headers, config) {
                callback();
            }).error(function(data, status, headers, config) {
                console.log(status + ' ' + headers);
            });
        };

    }]);
