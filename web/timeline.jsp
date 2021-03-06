<%@ page import="model.ArticleDB" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>time line</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <style type="text/css">
        .navbar-fixed-top {
            margin-top: 10px;
        }

        .navbar-brand {
            padding: 5px 35px;
        }

        .logo {
            width: 120px;
            height: 35px;
        }

        .navbar-button {
            margin-top: 5px;
            margin-right: 15px;
        }

        .list {
            padding-top: 70px;
        }

        .img-responsive {
            max-height: 400px;
        }
    </style>
</head>

<body>
<div class="container-fluid" id="main">
    <div class="navbar navbar-fixed-top">
        <div class="navbar-header">
            <a href="timeline.jsp" class="navbar-brand"><img src="fbkillerLogo.png" class="logo"></a>
        </div>
        <div class="navbar-fixed-top">
            <form method="get" action="/Logout">
                <button class="btn btn-primary navbar-button pull-right">로그아웃</button>
            </form>
            <button class="btn btn-primary navbar-button pull-right" onclick="location.href='write.html'">글쓰기
            </button>
        </div>
    </div>

    <div class="container list">
        <%
            User user = (User) session.getAttribute("user");
            ArticleDB db = new ArticleDB();
            db.connect();
            db.selectAll();
            try {
                while (db.getResult().next()) {
                    String title = db.getResult().getString("head");
                    String body = db.getResult().getString("body");
                    String author = db.getResult().getString("author");
                    String reason = null;
                    boolean ban;
                    if (db.getResult().getInt("ban") == 1) {
                        ban = true;
                        reason = db.getResult().getString("reason");
                    } else {
                        ban = false;
                    }
                    if (ban) {
        %>
        <div class="panel panel-info">
            <div class="panel-heading">
                <a href="#" class="pull-right">삭제</a>
                <h4>이 게시물은 차단되었습니다</h4>
            </div>
            <div class="panel-body">
                <hr>
                <%=reason%>
            </div>
        </div>
        <%
        } else {
        %>
        <div class="panel panel-info">
            <div class="panel-heading">
                <a href="#" class="pull-right">삭제</a>
                <h4><%=title%>
                </h4>
            </div>
            <div class="panel-body">
                <p>
                    <img src="files/<%=db.getResult().getString("authorprofile")%>"
                         class="img-circle" width="50px" height="50px">
                    <a href="#"><%=author%>
                    </a></p>
                <hr>
                <%=body%>
                <%
                    String imageSource = db.getResult().getString("image");
                    if (!imageSource.equals("")) {
                %>
                <br>
                <img src="files/<%=imageSource%>" class="img-responsive">
                <%
                    }
                %>
            </div>
        </div>
        <%
                }

            }

            if (!db.getResult().next()) {
        %>
        <div class="panel panel-info">
            <div class="panel-heading">
                <h4>더 이상 등록된 글이 없습니다</h4>
            </div>
            <div class="panel-body">
                <hr>
                더 이상 등록된 글이 없네요! 지금 글을 쓰러 가보실래요?
                <br>
                <br>
            </div>
        </div>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        %>
    </div>
    <%
        db.closeDB();
    %>
</div>
</body>

</html>
