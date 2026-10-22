<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.internship.model.Internship" %>

<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect(request.getContextPath() + "/admin-login.jsp");
        return;
    }

@SuppressWarnings("unchecked")
List<Internship> internships =
    (List<Internship>) request.getAttribute("internships");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Internships</title>
    <link rel="stylesheet" href="../css/admin.css">
</head>

<body>

	<%@ include file="../includes/admin-navbar.jsp" %>
    <h2>Manage Internships</h2>

    <h3>Add New Internship</h3>

    <form action="<%= request.getContextPath() %>/admin/add-internship"
          method="post">

        <label>Title:</label>
        <input type="text" name="title" required>

        <br><br>

        <label>Description:</label>
        <textarea name="description"></textarea>

        <br><br>

        <label>Required Skills:</label>
        <textarea name="requiredSkills"></textarea>

        <br><br>

        <label>Location:</label>
        <input type="text" name="location">

        <br><br>

        <label>Stipend:</label>
        <input type="number" name="stipend" step="0.01">

        <br><br>

        <label>Duration:</label>
        <input type="text" name="duration">

        <br><br>

        <label>Deadline:</label>
        <input type="date" name="deadline" required>

        <br><br>

        <input type="submit" value="Add Internship">

    </form>

    <hr>

    <h3>All Internships</h3>

    <%
        if (internships == null || internships.isEmpty()) {
    %>

        <p>No internships available.</p>

    <%
        } else {

            for (int i = 0; i < internships.size(); i++) {
    %>

        <div>

            <p>
                <strong>Internship ID:</strong>
                <%= internships.get(i).getInternshipId() %>
            </p>

            <p>
                <strong>Title:</strong>
                <%= internships.get(i).getTitle() %>
            </p>

            <p>
                <strong>Description:</strong>
                <%= internships.get(i).getDescription() %>
            </p>

            <p>
                <strong>Required Skills:</strong>
                <%= internships.get(i).getRequiredSkills() %>
            </p>

            <p>
                <strong>Location:</strong>
                <%= internships.get(i).getLocation() %>
            </p>

            <p>
                <strong>Stipend:</strong>
                <%= internships.get(i).getStipend() %>
            </p>

            <p>
                <strong>Duration:</strong>
                <%= internships.get(i).getDuration() %>
            </p>

            <p>
                <strong>Deadline:</strong>
                <%= internships.get(i).getDeadline() %>
            </p>

            <p>
                <strong>Status:</strong>
                <%= internships.get(i).getStatus() %>
            </p>

            <a href="<%= request.getContextPath() %>/admin/edit-internship?id=<%= internships.get(i).getInternshipId() %>">
                Edit
            </a>

            <br><br>

            <form action="<%= request.getContextPath() %>/admin/close-internship"
                  method="post">

                <input type="hidden"
                       name="internshipId"
                       value="<%= internships.get(i).getInternshipId() %>">

                <input type="submit" value="Close Internship">

            </form>

            <br>

            <form action="<%= request.getContextPath() %>/admin/delete-internship"
                  method="post">

                <input type="hidden"
                       name="internshipId"
                       value="<%= internships.get(i).getInternshipId() %>">

                <input type="submit"
                       value="Delete Internship"
                       onclick="return confirm('Are you sure you want to delete this internship?');">

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