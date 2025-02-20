<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.progress.jspandjstl.model.Tasks" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modify Task</title>
    <script>
        function convertDateAndSubmit() {
            // Get the value of the date input
            var dateInput = document.getElementById('date').value;

            // Convert the string input to a JavaScript Date object
            var dateObject = new Date(dateInput);

            // Check if the date is valid
            if (isNaN(dateObject.getTime())) {
                alert("Invalid date format. Please use YYYY-MM-DD HH:mm:ss.");
                return false;  // Prevent form submission if the date is invalid
            }

            // If valid, format the date to "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
            var formattedDate = dateObject.toISOString();  // Format to ISO 8601

            // Update the hidden input with the correct formatted date
            document.getElementById('date').value = formattedDate;

            // Submit the form
            document.getElementById('modifyForm').submit();
            return false; // Prevent the default form submission to handle it with JavaScript
        }
    </script>
</head>
<body>
<h1>Modify Task</h1>
<form id="modifyForm" action="/tasks/update" method="post" onsubmit="return convertDateAndSubmit()">
    <input type="hidden" name="taskid" id="taskid" value="${task.taskid}" />

    <label for="task">Task Name:</label>
    <input type="text" name="task" id="task" value="${task.task}" required/><br/>

    <label for="date">Date:</label>
    <input type="text" name="date" id="date" value="${task.date}" required/><br/>

    <input type="submit" value="Save Changes"/>
</form>
</body>
</html>
