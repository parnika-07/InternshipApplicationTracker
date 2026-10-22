<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect(
            request.getContextPath() + "/admin-login.jsp"
        );
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="../css/admin.css">
</head>

<body>

    <%@ include file="../includes/admin-navbar.jsp" %>

    <h2>Admin Dashboard</h2>

    <h3>Welcome, Admin!</h3>

    <p>
        Use the menu above to manage students, internships,
        and applications.
    </p>

</body>
</html>