<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/todo.css">
    <!-- Include the AngularJS library -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
</head>

<body ng-app="todoList">
<div ng-controller="todoListCtrl">

    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Todo List</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="well well-sm">
            <!-- Form Add Task -->
            <div id="add-task-form" class="row">
                <div class="col-xs-12">
                    <form class="form-inline">
                        <div class="form-group">
                            <input type="text" class="form-control" ng-model="newTask" placeholder="Add a task">
                        </div>
                        <button type="submit" class="btn btn-primary" ng-click="addTask(newTask)">Add task</button>
                    </form>
                    <br/>
                </div>
            </div>

            <!-- Filters -->
            <div id="filters" class="row">
                <div class="col-xs-12">
                    <form class="form-inline" ng-hide="displayEditMode">
                        <button type="button" class="btn btn-default filter" ng-click="getAllTasks()">
                            <span>All tasks</span>
                        </button>
                        <button type="button" class="btn btn-default filter" ng-click="getUncompletedTasks()">
                            <span>Active tasks</span>
                        </button>
                        <button type="button" class="btn btn-default filter" ng-click="getCompletedTasks()">
                            <span>Completed tasks</span>
                        </button>
                    </form>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-xs-12">
                <div ng-repeat="task in tasks">

                    <!-- Form Non Edit Mode -->
                    <form class="form-inline" ng-hide="displayEditMode">
                        <div class="form-group">
                            <input type="checkbox" ng-model="taskCompleted"
                                   ng-init="taskCompleted=(task.state === 1)" ng-click="changeState(task)">
                        </div>
                        <div class="form-group" ng-class="{ 'line-through': taskCompleted }">
                            <div>{{ task.description }}</div>
                        </div>
                        <button type="button" class="btn btn-default btn-s" ng-click="displayEditMode=!displayEditMode"
                                ng-hide="taskCompleted">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        </button>
                        <button type="button" class="btn btn-default btn-s" ng-click="removeTask(task, $index); ">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        </button>
                    </form>

                    <!-- Form Edit Mode -->
                    <form class="form-inline" ng-show="displayEditMode">
                        <div class="form-group">
                            <input type="text" class="form-control" ng-model="task.description">
                        </div>
                        <button type="button" class="btn btn-default btn-s"
                                ng-click="editTask(task); displayEditMode=!displayEditMode">
                            <span class="glyphicon glyphicon-save" aria-hidden="true"></span> Save task
                        </button>
                    </form>

                </div>
            </div>
        </div>


    </div>
</div>

<!-- Modules -->
<script src="js/app.js"></script>

<!-- Controllers -->
<script src="js/controllers/controllers.js"></script>

<!-- Services -->
<script src="js/services/services.js"></script>

<!-- Directives -->
</body>
</html>