<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.internship.model.Internship" %>

<%
    @SuppressWarnings("unchecked")
    List<Internship> internships =
        (List<Internship>) request.getAttribute("internships");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Browse Internships</title>
    <link rel="stylesheet" href="../css/style.css">
</head>

<body>

    <%@ include file="../includes/navbar.jsp" %>

    <h2>Available Internships</h2>

    <%
        if (internships == null || internships.isEmpty()) {
    %>

        <p>No internships available.</p>

    <%
        } else {

            for (int i = 0; i < internships.size(); i++) {
    %>

        <div>

            <h3>
                <%= internships.get(i).getTitle() %>
            </h3>

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

            <%
                if ("OPEN".equalsIgnoreCase(
                        internships.get(i).getStatus())) {
            %>

                <form action="<%= request.getContextPath() %>/student/apply-internship"
                      method="post">

                    <input type="hidden"
                           name="internshipId"
                           value="<%= internships.get(i).getInternshipId() %>">

                    <input type="submit"
                           value="Apply">

                </form>

            <%
                }
            %>

            <hr>

        </div>

    <%
            }
        }
    %>

</body>
</html>