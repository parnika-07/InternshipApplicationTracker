<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.internship.model.Student" %>

<%
    Student student = (Student) session.getAttribute("student");

    if (student == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
</head>

<body>
	
	<%@ include file="../includes/navbar.jsp" %>
    <h2>Student Dashboard</h2>

    <h3>Welcome, <%= student.getName() %>!</h3>

    <p>Username: <%= student.getUsername() %></p>

    <hr>

    <h3>Student Menu</h3>

    <p>
        <a href="<%= request.getContextPath() %>/student/profile">
            View Profile
        </a>
    </p>

    <p>
        <a href="<%= request.getContextPath() %>/student/browse-internships">
            Browse Internships
        </a>
    </p>

    <p>
        <a href="<%= request.getContextPath() %>/student/my-applications">
            My Applications
        </a>
    </p>

    <p>
        <a href="<%= request.getContextPath() %>/logout">
            Logout
        </a>
    </p>

</body>
</html>