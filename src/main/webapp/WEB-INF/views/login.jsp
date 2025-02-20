<%@page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!doctype html>
<html>
<head>
    <title>login page</title>
</head>
<body>
<p>Your session ID is: ${sessionId}</p>
<form>
    <input type="text" name="userid" placeholder="User ID" /><br>
    <input type="password" name="password" placeholder="Password" /><br>
    <button>Submit</button>

</form>
</body>
</html>
