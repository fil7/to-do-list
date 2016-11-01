
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
    <title>ToDo List</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
    <a href="../../index.jsp">Back to main menu</a>
    <br />

    <h2>TODO List</h2>

    <c:if test="${!empty listTasks}">
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="300">Description</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${listTasks}" var="task">
                <tr>
                    <td>${task.id}</td>
                    <td><a href="/taskdata/${task.id}" target="_blank">${task.description}</a></td>
                    <td><a href="<c:url value='/edit/${task.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/remove/${task.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>


    <h2>Add new task</h2>

    <c:url var="addAction" value="/tasks/add"/>

    <form:form action="${addAction}" commandName="task">
        <table>
            <c:if test="${!empty task.description}">
                <tr>
                    <td>
                        <form:label path="id">
                            <spring:message text="ID"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" size="8" disabled="true"/>
                        <form:hidden path="id"/>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <form:label path="description">
                        <spring:message text="Description"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="description"/>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <c:if test="${!empty task.description}">
                        <input type="submit"
                               value="<spring:message text="Edit Task"/>"/>
                    </c:if>
                    <c:if test="${empty task.description}">
                        <input type="submit"
                               value="<spring:message text="Add Task"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
