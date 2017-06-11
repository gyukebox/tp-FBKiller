<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <title>로그인</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body>
<%
    String loginmsg = (String) request.getAttribute("error");
    if (loginmsg != null) {
        out.println("<font color=red size=4px" + loginmsg + "</font>");
    }
%>
<div class="container col-lg-offset-3 col-lg-6">
    <form class="form-signin" method="get" action="/Login">
        <h2>Please sign in</h2>
        <label for="id" class="sr-only">ID</label>
        <input type="text" name="id" id="id" class="form-control" placeholder="User ID" required="" autofocus="">
        <label for="pw" class="sr-only">Password</label>
        <input type="password" name="pw" id="pw" class="form-control" placeholder="Password" required="">
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary" onclick="location.href='register.html'">Register</button>
        <button class="btn btn-lg btn-primary pull-right">Sign in</button>
    </form>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

</body>
</html>