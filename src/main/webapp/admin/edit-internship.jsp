<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.internship.model.Internship" %>

<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect(request.getContextPath() + "/admin-login.jsp");
        return;
    }

    Internship internship =
        (Internship) request.getAttribute("internship");

    if (internship == null) {
        response.sendRedirect(
            request.getContextPath() + "/admin/internships"
        );
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Internship</title>
    <link rel="stylesheet" href="../css/admin.css">
</head>

<body>

	<%@ include file="../includes/admin-navbar.jsp" %>
    <h2>Edit Internship</h2>

    <form action="<%= request.getContextPath() %>/admin/edit-internship"
          method="post">

        <input type="hidden"
               name="internshipId"
               value="<%= internship.getInternshipId() %>">

        <label>Title:</label>
        <input type="text"
               name="title"
               value="<%= internship.getTitle() %>"
               required>

        <br><br>

        <label>Description:</label>
        <textarea name="description"><%= internship.getDescription() != null
            ? internship.getDescription() : "" %></textarea>

        <br><br>

        <label>Required Skills:</label>
        <textarea name="requiredSkills"><%= internship.getRequiredSkills() != null
            ? internship.getRequiredSkills() : "" %></textarea>

        <br><br>

        <label>Location:</label>
        <input type="text"
               name="location"
               value="<%= internship.getLocation() != null
                   ? internship.getLocation() : "" %>">

        <br><br>

        <label>Stipend:</label>
        <input type="number"
               name="stipend"
               step="0.01"
               value="<%= internship.getStipend() %>">

        <br><br>

        <label>Duration:</label>
        <input type="text"
               name="duration"
               value="<%= internship.getDuration() != null
                   ? internship.getDuration() : "" %>">

        <br><br>

        <label>Deadline:</label>
        <input type="date"
               name="deadline"
               value="<%= internship.getDeadline() %>"
               required>

        <br><br>

        <label>Status:</label>

        <select name="status">

            <option value="OPEN"
                <%= "OPEN".equalsIgnoreCase(internship.getStatus())
                    ? "selected" : "" %>>
                OPEN
            </option>

            <option value="CLOSED"
                <%= "CLOSED".equalsIgnoreCase(internship.getStatus())
                    ? "selected" : "" %>>
                CLOSED
            </option>

        </select>

        <br><br>

        <input type="submit" value="Update Internship">

    </form>

    <br>

    <a href="<%= request.getContextPath() %>/admin/internships">
        Back to Internships
    </a>

</body>
</html>