<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<nav>

    <a href="<%= request.getContextPath() %>/student/dashboard">
        Dashboard
    </a>

    |

    <a href="<%= request.getContextPath() %>/student/profile">
        Profile
    </a>

    |

    <a href="<%= request.getContextPath() %>/student/browse-internships">
        Internships
    </a>

    |

    <a href="<%= request.getContextPath() %>/student/my-applications">
        My Applications
    </a>

    |

    <a href="<%= request.getContextPath() %>/logout">
        Logout
    </a>

</nav>

<hr>