<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.internship.model.Application" %>

<%
    @SuppressWarnings("unchecked")
    List<Application> applications =
        (List<Application>) request.getAttribute("applications");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Applications</title>
    <link rel="stylesheet" href="../css/style.css">
</head>

<body>
	
	<%@ include file="../includes/navbar.jsp" %>
    <h2>My Applications</h2>

    <%
        if (applications == null || applications.isEmpty()) {
    %>

        <p>You have not applied for any internships yet.</p>

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
                <strong>Internship ID:</strong>
                <%= applications.get(i).getInternshipId() %>
            </p>

            <p>
                <strong>Applied Date:</strong>
                <%= applications.get(i).getAppliedDate() %>
            </p>

            <p>
                <strong>Status:</strong>
                <%= applications.get(i).getStatus() %>
            </p>

            <form action="<%= request.getContextPath() %>/student/withdraw-application"
                  method="post">

                <input type="hidden"
                       name="applicationId"
                       value="<%= applications.get(i).getApplicationId() %>">

                <input type="submit"
                       value="Withdraw"
                       onclick="return confirm('Are you sure you want to withdraw this application?');">

            </form>

            <hr>

        </div>

    <%
            }
        }
    %>

    <br>

    <a href="<%= request.getContextPath() %>/student/dashboard">
        Back to Dashboard
    </a>

</body>
</html>