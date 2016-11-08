<html ng-app="TodoList">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/todo.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
    <script src="js/controllers.js" ></script>
</head>

<body>
<div ng-controller="todoListCtrl">

    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Todo List</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <form class="form-inline">
                    <div class="form-group">
                        <input type="text" class="form-control" ng-model="newTask" placeholder="I need to ...">
                    </div>
                    <button type="submit" class="btn btn-primary" ng-click="addTask(newTask)">Add task</button>
                </form>
                <br />
                <br />
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <div ng-repeat="task in tasks">

                    <!-- Form Non Edit Mode -->
                    <form class="form-inline" ng-hide="displayEditMode">
                        <div class="form-group">
                            <input type="checkbox" ng-model="taskCompleted">
                        </div>
                        <div class="form-group" ng-class="{ 'line-through': taskCompleted }">
                            <div>{{ task.description }}</div>
                        </div>
                        <button type="button" class="btn btn-default btn-s" ng-click="displayEditMode=!displayEditMode" ng-hide="taskCompleted">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        </button>
                        <button type="button" class="btn btn-default btn-s" ng-click="removeTask(task, $index)">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        </button>
                    </form>

                    <!-- Form Edit Mode -->
                    <form class="form-inline" ng-show="displayEditMode">
                        <div class="form-group">
                            <input type="text" class="form-control" ng-model="task.description">
                        </div>
                        <button type="button" class="btn btn-default btn-s" ng-click="displayEditMode=!displayEditMode">
                            <span class="glyphicon glyphicon-save" aria-hidden="true"></span> Save task
                        </button>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>