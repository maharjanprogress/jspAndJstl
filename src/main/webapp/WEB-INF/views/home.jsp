<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Task List</title>
</head>
<body>
<c:out value="${'progress'}"></c:out>
<h1>Tasks</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Date</th>
    </tr>
    </thead>
    <tbody>

    <!-- Use JSTL to iterate over the tasks -->
    <c:forEach var="task" items="${tasks}">
        <tr>
            <td>${task.taskid}</td>
            <td>${task.task}</td>
            <td>${task.date}</td>
            <td>
                <!-- Modify Button -->

                <a href="/tasks/modify/${task.taskid}">
                    <button style="padding: 10px 20px; background-color: #4CAF50; color: white; border: none; cursor: pointer;">
                        Modify
                    </button>
                </a>

                <!-- Delete Button -->
                <form action="/tasks/delete/${task.taskid}" method="get" style="display:inline;">
                    <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this task?');"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- Add Task Button -->
<a href="/tasks/add" style="text-decoration: none;">
    <button style="padding: 10px 20px; background-color: #4CAF50; color: white; border: none; cursor: pointer;">
        Add Task
    </button>
</a>
</body>
</html>