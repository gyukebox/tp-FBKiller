<%@ page import="model.ArticleDB" %>
<%@ page import="java.sql.SQLException" %>
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
    <!--상단바-->
    <div class="navbar navbar-fixed-top">
        <div class="navbar-header">
            <a href="timeline.jsp" class="navbar-brand"><img src="fbkillerLogo.png" class="logo"></a>
        </div>
        <!--
        <nav class="collapse navbar-collapse" role="navigation">
            <form class="navbar-form">
                <div class="input-group input-group-sm">
                    <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </nav>
        -->
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
                <a href="#" class="pull-right">View all</a>
                <h4>이 게시물은 차단되었습니다</h4>
            </div>
            <div class="panel-body">
                <hr>
                차단 원인 :
                <%
                    out.println(reason);
                %>
            </div>
        </div>
        <%
        } else {
        %>
        <div class="panel panel-info">
            <div class="panel-heading">
                <a href="#" class="pull-right">View all</a>
                <h4>
                    <%
                        out.println(title);
                    %>
                </h4>
            </div>
            <div class="panel-body">
                <p>
                    <img src="https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-1/p240x240/15135948_918161824980506_1839427576752821065_n.jpg?oh=e0deee95aac348a7d8a23ef8a55fe803&oe=59A04084"
                         class="img-circle" width="50px" height="50px">
                    <a href="#">
                        <%
                            out.println(author);
                        %>
                    </a></p>
                <hr>
                <%
                    out.println(body);
                %>

            </div>
        </div>
        <%
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        %>
        <div class="panel panel-info">
            <div class="panel-heading">
                <a href="#" class="pull-right">View all</a>
                <h4>아직 등록된 글이 없습니다</h4>
            </div>
            <div class="panel-body">
                <p>
                    <img src="https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-1/p240x240/15135948_918161824980506_1839427576752821065_n.jpg?oh=e0deee95aac348a7d8a23ef8a55fe803&oe=59A04084"
                         class="img-circle" width="50px" height="50px">
                    <a href="#"></a></p>
                <hr>
                아직 등록된 글이 없네요! 지금 글을 쓰러 가보실래요?
                <br>
                <br>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <%
        db.closeDB();
    %>

    <!--
    <div class="container list">
        <div class="panel panel-info">
            <div class="panel-heading">
                <a href="#" class="pull-right">View all</a>
                <h4>고언어 할싸람~~??</h4>
            </div>
            <div class="panel-body">
                <p>
                    <img src="https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-1/p240x240/15135948_918161824980506_1839427576752821065_n.jpg?oh=e0deee95aac348a7d8a23ef8a55fe803&oe=59A04084"
                         class="img-circle" width="50px" height="50px">
                    <a href="#"></a></p>
                <hr>
                안녕하세요. 많은 분들의 도움으로 이번에
                &lt;Go 웹 프로그래밍&gt;이라는 역서를 출간하게 됐습니다. 이 책은 "Go를 이용해 웹 애플리케이션 서버를 어떻게 구현할 수 있을까?"에 대한 대답을 맨땅에 헤딩하는 방식으로
                접근합니다.
                프레임워크를 이용하지 않고 Go언어만으로 서버 개발을 진행하기 때문에 애플리케이션 서버에서 갖춰야하는 세션처리, DB처리, 템플릿 처리등과 같은 디테일한 처리를 순수 Go언어와
                관련 패키지 조합으로 어떻게 처리할 수 있는지를 살펴볼 수 있습니다.
                <br>
                <br>
                <img src="http://image.yes24.com/momo/TopCate1124/MidCate008/112371185.jpg" class="img-responsive">
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">
                <a href="#" class="pull-right">View all</a>
                <h4><a>유효린님</a>이 새로운 사진 2장을 추가했습니다 — <a>정근영</a>님, <a>고상원님</a>과 함께.</h4>
            </div>
            <div class="panel-body">
                <p>
                    <img src="https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-1/p240x240/17155858_1239048372883581_1789293228304069930_n.jpg?oh=f94d496880e02f47f59fc84257a58809&oe=59D50416"
                         class="img-circle" width="50px" height="50px">
                    <a href="#">정근영</a></p>
                <hr>
                💕 충대부고 28&29 회장단 크로스 💕 너무 꿀잼~ 담에도 또먹자~ <a>#동네술집</a> <a>#앙기모띠</a>
                <br>
                <br>
                <img src="https://scontent-hkg3-1.xx.fbcdn.net/v/t31.0-0/p600x600/16300427_1343033895771994_3885513944975454310_o.jpg?oh=594ed4552e66e98a641d981f27f2d077&oe=59D5EEAF"
                     class="img-responsive">
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">
                <a href="#" class="pull-right">View all</a>
                <h4><a>#시공조아</a></h4>
            </div>
            <div class="panel-body">
                <p>
                    <img src="https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/p240x240/16831971_1876563345890882_4827710239855310899_n.jpg?oh=da5b830b98923db71e19cbcbf116101d&oe=59D5F47B"
                         class="img-circle" width="50px" height="50px">
                    <a href="#">김병찬</a></p>
                <hr>
                당신이 있는 시공을 찾고 있어. #너의_시공은 시공을 함께 할 운명의 그 사람, 당신의 시공은 누구인가요? '너의 시공은' 영상을 #시공조아 해시태그와 함께 '전체 공개'로 공유하고,
                댓글로 여러분과 시공을 함께할 @친구를 소환해주세요. 추첨을 통해 '시공의 선물'을 드립니다.
                <br>
                <br>
                <img src="" class="img-responsive">
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">
                <a href="#" class="pull-right">View all</a>
                <h4>제목이니까 쫌 큼지막한 TEXT</h4>
            </div>
            <div class="panel-body">
                <p>
                    <img src="https://scontent.xx.fbcdn.net/v/t1.0-1/p100x100/18671246_1435053916550358_4270816788354613889_n.jpg?oh=1d606e3ca5459970f5b7967a0f485597&oe=59D76533"
                         class="img-circle" width="50px" height="50px">
                    <a href="#">이재원</a></p>
                <hr>
                아무글이나 싸질러 놓은것과 다름이 없는 그저그런 그냥 대잔치스러운 개소리를 적어놓은곳. 무슨말을 하던지 코드에는 전혀 상관이 없으며 비어있으면 허전한 느낌이 없지않아 있기때문에,
                테스트용으로 한글도 되나 싶어서 막 써 본 글이다.
                <br>
                <br>
                <img src="" class="img-responsive">
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">
                <a href="#" class="pull-right">View all</a>
                <h4>카톡으로 협박문자를 햇는데 모르는 사람이 남친 있냐고하고 이름 모냐고 물어보니 아무말 안하고 저보고 지옥간다</h4>
            </div>
            <div class="panel-body">
                <p><img src="http://kinimage.naver.net/exphoto/expert/9/qjqanhaneum_1460679381382.jpg?type=f130_130"
                        class="img-circle" width="50px" height="50px">
                    <a href="#">허원제</a></p>
                <hr>
                안녕하세요. 로시컴-네이버 지식iN 상담변호사 허원제 입니다. 협박죄는 상대방에게 공포심을 일으킬 목적으로 생명이나, 신체 그리고 자유나 명예 등에 위해를 가할 것을 통고하는 것을
                의미합니다. 또한 상대방이 사실상 공포심을 느꼈는지는 중요하지 않은데요.질문자님 처럼 "지옥간다", "안좋은일 생길거야"등은 단순한 경고일 뿐이므로 협박죄에서의 협박은 아닙니다. 그리고
                재앙이나 운수는 말하는 사람에 의하여 좌우될 수 있는 것이 아니기 때문입니다. 감사합니다.
                <br>
                <br><img src="" class="img-responsive">
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading">
                <a href="#" class="pull-right">View all</a>
                <h4>평생 모은 재산, 제가 상속 받을 수 있을까요?</h4>
            </div>
            <div class="panel-body">
                <p><img src="http://kinimage.naver.net/exphoto/expert/98/winsosong_1403241276876.jpg?type=f200_200"
                        class="img-circle" width="50px" height="50px">
                    <a href="#">송명호</a></p>
                <hr>
                양가의 축복 속에 결혼식을 올리고 20년 정도를 살면서 10억짜리 아파트와 10억 정도 되는 상가 등 20억 이상되는 재산을 모았습니다. 아파트와 상가는 남편이름으로 되어있구요. 남편이
                깊은 병이 들어 1년간 병간호를 하였지만 저 세상으로 떠났습니다. 자식이 없어서 혼인신고를 하지 않고 살았는데 초상집에 어떤 여자가 남편의 아이를 데리고 왔습니다. 남편은 그 여자와 이미
                4년 전에 저 몰래 혼인신고까지 하였습니다. 남편과 제가 평생 모은 재산을 제가 상속 받을 수 있을까요?
                <br>
                <br><img src="" class="img-responsive">
            </div>
        </div>
    </div>
    -->

</div>
</body>

</html>
