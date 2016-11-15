'use strict';

app.controller('TodoListController', ['$scope', 'TaskRequest', 'TaskFilter',
    function($scope, TaskRequest, TaskFilter) {

        $scope.page = 0;
        $scope.pageNumber = 7;
        $scope.priority = 0; // Normal
        $scope.priorities = [
            {value: '0', name: 'Low'},
            {value: '1', name: 'Medium'},
            {value: '2', name: 'High'}
        ];

        /** CRUD **/

        $scope.addTask = function($event) {
            if ($event.keyCode == 13 && $scope.newTask) {
                var task = {description: $scope.newTask, priority: $scope.priority};
                TaskRequest.save(task, function() {
                    $scope.tasks.push(task);
                });
                $scope.newTask = '';
            }
        };

        $scope.removeTask = function(task, $index) {
            TaskRequest.delete({id: task.id}, function() {
                $scope.tasks.splice($index, 1);
            });

        };
        $scope.editTask = function(task) {
            TaskRequest.update(task);
        };

        $scope.changeState = function(task) {
            task.state = !task.state;
            TaskRequest.update(task);
        };

        /** Filters **/

        $scope.filter = 'active';

        $scope.getAllTasks = function($event) {
            $scope.filter = 'all';
            showTasks($event);
        };

        $scope.getActiveTasks = function($event) {
            $scope.filter = 'active';
            showTasks($event);
        };

        $scope.getCompletedTasks = function($event) {
            $scope.filter = 'completed';
            showTasks($event);
        };

        var getPage = function(startIndex, callback) {
            TaskFilter.getPage({
                filter: $scope.filter,
                start: startIndex,
                size: $scope.pageNumber
            }, function(data) {
                callback(data);
            });
        };

        function showTasks($event) {
            doActiveFilter($event);
            $scope.page = 0;
            getPage($scope.page * $scope.pageNumber, function(data) {
                $scope.tasks = data;
            });
        }

        function doActiveFilter($event) {
            if ($event) {
                document.querySelector('.active-filter').classList.remove('active-filter');
                $event.currentTarget.classList.add('active-filter');
            }
        }

        /** Pagination Controller **/

        $scope.prevPage = function() {
            if ($scope.page - 1 >= 0) {
                var startIndex = --$scope.page * $scope.pageNumber;
                getPage(startIndex, function(data) {
                    $scope.tasks = data;
                });
            }
        };

        $scope.nextPage = function() {
            var startIndex = ($scope.page + 1) * $scope.pageNumber;
            getPage(startIndex, function(data) {
                if (data.length > 0) {
                    $scope.page++;
                    $scope.tasks = data;
                }
            });
        };

        $scope.getActiveTasks();

    }]);