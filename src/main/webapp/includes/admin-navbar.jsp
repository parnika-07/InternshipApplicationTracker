<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<nav>

    <a href="<%= request.getContextPath() %>/admin/dashboard">
        Dashboard
    </a>

    |

    <a href="<%= request.getContextPath() %>/admin/students">
        Students
    </a>

    |

    <a href="<%= request.getContextPath() %>/admin/internships">
        Internships
    </a>

    |

    <a href="<%= request.getContextPath() %>/admin/applications">
        Applications
    </a>

    |

    <a href="<%= request.getContextPath() %>/logout">
        Logout
    </a>

</nav>

<hr>