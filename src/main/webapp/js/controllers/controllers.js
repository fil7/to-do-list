'use strict';

app.controller('TodoListController', ['$scope', 'TaskService',
    function($scope, TaskService) {

        $scope.page = 0;
        $scope.pageNumber = 7;
        $scope.filter = '/active';
        $scope.priority = 0; // Normal
        $scope.priorities = [
            {value: '0', name: 'Low'},
            {value: '1', name: 'Medium'},
            {value: '2', name: 'High'}
        ];

        var getTasksUrl = '/rest/tasks';
        var addTaskUrl = '/rest/tasks/add';
        var removeTaskUrl = '/rest/remove/task';
        var editTaskUrl = '/rest/edit/task';
        var completeTaskUrl = '/rest/change-task-state';

        /** CRUD **/

        $scope.addTask = function($event) {
            if ($event.keyCode == 13 && $scope.newTask) {
                var addedTask = {description: $scope.newTask, priority: $scope.priority};
                TaskService.postRequest(addTaskUrl,
                    addedTask, function() {
                        $scope.tasks.unshift(addedTask);
                    });
                $scope.newTask = '';
            }
        };

        $scope.removeTask = function(task, $index) {
            TaskService.postRequest(removeTaskUrl,
                {id: task.id, description: task.description}, function() {
                    $scope.tasks.splice($index, 1);
                });
        };
        $scope.editTask = function(task) {
            TaskService.postRequest(editTaskUrl,
                {id: task.id, description: task.description, priority: task.priority},
                function() {
                });
        };

        $scope.changeState = function(task) {
            TaskService.postRequest(completeTaskUrl,
                {id: task.id, description: task.description, state: !task.state},
                function() {
                });
        };


        /** Filters **/

        $scope.getAllTasks = function($event) {
            $scope.filter = '/all';
            showTasks($event);
        };

        $scope.getActiveTasks = function($event) {
            $scope.filter = '/active';
            showTasks($event);
        };

        $scope.getCompletedTasks = function($event) {
            $scope.filter = '/completed';
            showTasks($event);
        };

        function showTasks($event) {
            doActiveFilter($event);
            clearPage();
            getTasks(getTasksUrl + $scope.filter + "/" + $scope.page * $scope.pageNumber + "/" + $scope.pageNumber);
        }

        function getTasks(url) {
            TaskService.getTasksByUrl(url)
                .success(function(data) {
                    $scope.tasks = data;
                });
        }

        function doActiveFilter($event) {
            if ($event) {
                document.querySelector('.active-filter').classList.remove('active-filter');
                $event.currentTarget.classList.add('active-filter');
            }
        }

        function clearPage() {
            $scope.page = 0;
        }

        /** Pagination Controller **/

        var paginationUrl = '/rest/tasks'; // /tasks/{filter}/{start}/{size}

        $scope.prevPage = function() {
            if ($scope.page - 1 >= 0) {
                var startIndex = --$scope.page * $scope.pageNumber;
                TaskService.getTasksByUrl(paginationUrl + $scope.filter + "/" + startIndex + '/' + $scope.pageNumber)
                    .success(function(data) {
                        $scope.tasks = data;
                    });
            }
        };

        $scope.nextPage = function() {
            var startIndex = ($scope.page + 1) * $scope.pageNumber;
            TaskService.getTasksByUrl(paginationUrl + $scope.filter + "/" + startIndex + '/' + $scope.pageNumber)
                .success(function(data) {
                    if (data.length > 0) {
                        $scope.page++;
                        $scope.tasks = data;
                    }
                });
        };


        $scope.getActiveTasks();

    }]);