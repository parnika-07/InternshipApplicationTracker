<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Login</title>
    <script src="js/script.js"></script>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <h2>Student Login</h2>

    <%
        String error = request.getParameter("error");

        if ("invalid".equals(error)) {
    %>
        <p>Invalid username or password.</p>
    <%
        }
    %>

    <form action="student/login" method="post" onsubmit="return validateForm();">

        <label>Username:</label>
        <input type="text" name="username" required>

        <br><br>

        <label>Password:</label>
        <input type="password" id="password" name="password" required>

        <br><br>

        <input type="submit" value="Login">

    </form>

    <br>

    <a href="register.jsp">New student? Register here</a>

</body>
</html>