<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.internship.model.Student" %>

<%
    Student student = (Student) request.getAttribute("student");

    if (student == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile</title>
    <link rel="stylesheet" href="../css/style.css">
</head>

<body>

	<%@ include file="../includes/navbar.jsp" %>
    <h2>My Profile</h2>

    <form action="<%= request.getContextPath() %>/student/update-profile"
          method="post">

        <input type="hidden"
               name="studentId"
               value="<%= student.getStudentId() %>">

        <label>Name:</label>
        <input type="text" name="name"
               value="<%= student.getName() %>" required>

        <br><br>

        <label>Username:</label>
        <input type="text" value="<%= student.getUsername() %>" disabled>

        <br><br>

        <label>Email:</label>
        <input type="email" name="email"
               value="<%= student.getEmail() %>" required>

        <br><br>

        <label>Phone:</label>
        <input type="text" name="phone"
               value="<%= student.getPhone() != null ? student.getPhone() : "" %>">

        <br><br>

        <label>Address:</label>
        <textarea name="address"><%= student.getAddress() != null ? student.getAddress() : "" %></textarea>

        <br><br>

        <label>Gender:</label>
        <input type="text" name="gender"
               value="<%= student.getGender() != null ? student.getGender() : "" %>">

        <br><br>

        <label>Qualification:</label>
        <input type="text" name="qualification"
               value="<%= student.getQualification() != null ? student.getQualification() : "" %>">

        <br><br>

        <label>Skills:</label>
        <textarea name="skills"><%= student.getSkills() != null ? student.getSkills() : "" %></textarea>

        <br><br>

        <label>Resume:</label>
        <input type="text"
               value="<%= student.getResume() != null ? student.getResume() : "" %>"
               disabled>

        <br><br>

        <input type="submit" value="Update Profile">

    </form>

    <br>

    <form action="<%= request.getContextPath() %>/student/delete-account"
          method="post">

        <input type="submit"
               value="Delete Account"
               onclick="return confirm('Are you sure you want to delete your account?');">

    </form>

    <br>

    <a href="<%= request.getContextPath() %>/student/dashboard">
        Back to Dashboard
    </a>

</body>
</html>