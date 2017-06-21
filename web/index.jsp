<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("/login.jsp");
    } else {
        response.sendRedirect("/timeline.jsp");
    }
%>
</body>
</html>
