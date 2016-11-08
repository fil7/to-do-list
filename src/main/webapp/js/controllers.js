'use strict';

angular.module('TodoList',[]);

angular.module('TodoList').controller('todoListCtrl', function($scope, $http) {

    $scope.tasks = [];

    $http.get('/rest/tasks').success(function(data) {
        $scope.tasks = data;
        console.log(data);
    });

    $scope.addTask = function(task) {
        $scope.tasks.push(task);
        $scope.newTask = '';
    };

    $scope.removeTask = function (task, $index) {
        $scope.tasks.splice($index, 1);
    };

});