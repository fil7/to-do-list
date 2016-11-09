'use strict';

app.controller('todoListCtrl', ['$scope', 'TaskService',
    function($scope, TaskService) {

        var allTasksUrl = '/rest/tasks';
        var completedTasksUrl = '/rest/tasks/completed-tasks';
        var uncompletedTasksUrl = '/rest/tasks/uncompleted-tasks';
        var addTaskUrl = '/rest/tasks/add';
        var removeTaskUrl = '/rest/remove/task';
        var editTaskUrl = '/rest/edit/task';
        var completeTaskUrl = '/rest/change-task-state';

        $scope.page = 1;
        $scope.pageNumber = 10;

        $scope.addTask = function(task) {
            var addedTask = {description: task, state: 0};
            TaskService.postRequest(addTaskUrl,
                JSON.stringify(addedTask), function() {
                    $scope.tasks.push(addedTask);
                });
            $scope.newTask = '';
        };

        $scope.removeTask = function(task, $index) {
            TaskService.postRequest(removeTaskUrl,
                JSON.stringify({id: task.id, description: task.description}), function() {
                    $scope.tasks.splice($index, 1);
            });
        };
        $scope.editTask = function(task) {
            TaskService.postRequest(editTaskUrl,
                JSON.stringify({id: task.id, description: task.description}), function(){});
        };

        $scope.changeState = function(task) {
            task.state = task.state ? 0 : 1;
            TaskService.postRequest(completeTaskUrl,
                JSON.stringify({id: task.id, description: task.description, state: task.state}), function(){});
        };

        $scope.getAllTasks = function() {
            TaskService.getTasksByUrl(allTasksUrl)
                .success(function(data) {
                    $scope.tasks = data;
                });
        };

        $scope.getCompletedTasks = function() {
            TaskService.getTasksByUrl(completedTasksUrl)
                .success(function(data) {
                    $scope.tasks = data;
                });
        };

        $scope.getUncompletedTasks = function() {
            TaskService.getTasksByUrl(uncompletedTasksUrl)
                .success(function(data) {
                    $scope.tasks = data;
                });
        };

        $scope.getUncompletedTasks();

    }]);