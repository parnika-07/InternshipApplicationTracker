<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
    <script src="js/script.js"></script>
    <link rel="stylesheet" href="css/admin.css">
</head>

<body>

    <h2>Admin Login</h2>

    <%
        String error = request.getParameter("error");

        if ("invalid".equals(error)) {
    %>
        <p>Invalid admin username or password.</p>
    <%
        }
    %>

    <form action="admin/login" method="post" onsubmit="return validateForm();">

        <label>Username:</label>
        <input type="text" name="username" required>

        <br><br>

        <label>Password:</label>
        <input type="password" id="password" name="password" required>

        <br><br>

        <input type="submit" value="Login">

    </form>

    <br>

    <a href="login.jsp">Student Login</a>

</body>
</html>