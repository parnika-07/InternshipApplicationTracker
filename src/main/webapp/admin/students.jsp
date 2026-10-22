<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.internship.model.Student" %>

<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect(request.getContextPath() + "/admin-login.jsp");
        return;
    }
	@SuppressWarnings("unchecked")
    List<Student> students =
        (List<Student>) request.getAttribute("students");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Students</title>
    <link rel="stylesheet" href="../css/admin.css">
</head>

<body>

	<%@ include file="../includes/admin-navbar.jsp" %>
    <h2>Manage Students</h2>

    <%
        if (students == null || students.isEmpty()) {
    %>

        <p>No students registered yet.</p>

    <%
        } else {

            for (int i = 0; i < students.size(); i++) {
    %>

        <div>

            <p>
                <strong>Student ID:</strong>
                <%= students.get(i).getStudentId() %>
            </p>

            <p>
                <strong>Name:</strong>
                <%= students.get(i).getName() %>
            </p>

            <p>
                <strong>Username:</strong>
                <%= students.get(i).getUsername() %>
            </p>

            <p>
                <strong>Email:</strong>
                <%= students.get(i).getEmail() %>
            </p>

            <p>
                <strong>Phone:</strong>
                <%= students.get(i).getPhone() %>
            </p>

            <p>
                <strong>Qualification:</strong>
                <%= students.get(i).getQualification() %>
            </p>

            <p>
                <strong>Skills:</strong>
                <%= students.get(i).getSkills() %>
            </p>

            <form action="<%= request.getContextPath() %>/admin/delete-student"
                  method="post">

                <input type="hidden"
                       name="studentId"
                       value="<%= students.get(i).getStudentId() %>">

                <input type="submit"
                       value="Delete Student"
                       onclick="return confirm('Are you sure you want to delete this student?');">

            </form>

            <hr>

        </div>

    <%
            }
        }
    %>

    <br>

    <a href="<%= request.getContextPath() %>/admin/dashboard">
        Back to Admin Dashboard
    </a>

</body>
</html>