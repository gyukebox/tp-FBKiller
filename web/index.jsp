<%--
  Created by IntelliJ IDEA.
  User: gyukebox
  Date: 2017. 6. 11.
  Time: 오전 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        if(session.getAttribute("id") == null) {
            response.sendRedirect("/login.jsp");
        } else {
            response.sendRedirect("/timeline.html");
        }
    %>
</body>
</html>
