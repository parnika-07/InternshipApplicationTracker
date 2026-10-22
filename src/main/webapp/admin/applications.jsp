<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.internship.model.Application" %>

<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect(request.getContextPath() + "/admin-login.jsp");
        return;
    }

    @SuppressWarnings("unchecked")
    List<Application> applications =
        (List<Application>) request.getAttribute("applications");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Applications</title>
    <link rel="stylesheet" href="../css/admin.css">
</head>

<body>
	<%@ include file="../includes/admin-navbar.jsp" %>
    <h2>Manage Applications</h2>

    <%
        if (applications == null || applications.isEmpty()) {
    %>

        <p>No applications received yet.</p>

    <%
        } else {

            for (int i = 0; i < applications.size(); i++) {
    %>

        <div>

            <p>
                <strong>Application ID:</strong>
                <%= applications.get(i).getApplicationId() %>
            </p>

            <p>
                <strong>Student ID:</strong>
                <%= applications.get(i).getStudentId() %>
            </p>

            <p>
                <strong>Internship ID:</strong>
                <%= applications.get(i).getInternshipId() %>
            </p>

            <p>
                <strong>Applied Date:</strong>
                <%= applications.get(i).getAppliedDate() %>
            </p>

            <p>
                <strong>Current Status:</strong>
                <%= applications.get(i).getStatus() %>
            </p>

            <form action="<%= request.getContextPath() %>/admin/update-application-status"
                  method="post">

                <input type="hidden"
                       name="applicationId"
                       value="<%= applications.get(i).getApplicationId() %>">

                <label>Update Status:</label>

                <select name="status">

                    <option value="PENDING"
                        <%= "PENDING".equalsIgnoreCase(applications.get(i).getStatus())
                            ? "selected" : "" %>>
                        PENDING
                    </option>

                    <option value="ACCEPTED"
                        <%= "ACCEPTED".equalsIgnoreCase(applications.get(i).getStatus())
                            ? "selected" : "" %>>
                        ACCEPTED
                    </option>

                    <option value="REJECTED"
                        <%= "REJECTED".equalsIgnoreCase(applications.get(i).getStatus())
                            ? "selected" : "" %>>
                        REJECTED
                    </option>

                </select>

                <input type="submit" value="Update">

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