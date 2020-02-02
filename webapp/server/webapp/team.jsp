<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body {
            margin: 0;
            background-color: white;
            overflow-x: hidden;
            overflow-y: hidden;
            background-repeat: no-repeat;
        }

        #header {
            height: 10%;
            width: 100%;
        }

        #lower_body {
            height: 90%;
            width: 100%;
            background-color: #ECECEC;
        }


        #team-members{
            padding-top: 85px;
            height: 75%;
            width: 100%;
            padding-left: 50px;
        }

        #member1,#member2,#member3,#member4{
            float: left;
            width: 20%;
            background-color: white;
            height: 95%;
            border-radius: 40px;
            margin-left: 30px;
            box-shadow: 10px 10px 15px rgba(65, 65, 65, 0.69);
        }

        #person-img{
            margin-left: 24px;
            margin-top: 15px;
            width: 80%;
            height: 45%;
            background-repeat: no-repeat;
            border-radius: 50%;
        }

        #person-details{
            width: 100%;
            height: 20%;
        }

        #social-symbols{
            width: 100%;
            height: 16%;
            border-radius: 0 0 40px 40px
        }

        #insta-logo{
            width: 50px;
            height:80%;
            float: left;
            margin-left: 38px;
        }

        #twitter-logo{
            width: 50px;
            height:80%;
            float: left;
            margin-left: 12px
        }

        #linkdin-logo{
            width: 50px;
            height:80%;
            float: left;
            margin-left: 15px
        }

    </style>
</head>
<title>
    TEAM
</title>
<body>
<div id="header">
    <%@include file="topnav.jsp" %>
</div>
<div id="lower_body">
    <div id="team-caption">

    </div>
    <div id="team-members">
        <div id="member1">
            <div id="person-img">
                <img src="static/images/shubham2.jpg" style="width: 100%; height: 185px;border-radius: 50%">
            </div>
            <div id="person-details">
                <div style="width: 100%;height: fit-content; text-align: center;
                margin-top: 40px">
                    <b style="color: #20509E; font:bold 18px Arial, Helvetica, sans-serif">
                        SHUBHAM TIWARI
                    </b><br><br>
                    <b style="color: #20509E; font: 16px Arial, Helvetica, sans-serif">
                        Project Manager
                    </b>
                </div>
            </div>
            <div id="social-symbols">
                <div id="insta-logo">
                    <img src="static/images/insta.png" style="width: 50px">
                </div>
                <div id="linkdin-logo">
                    <img src="static/images/linkdin.png" style="width: 50px">
                </div>
                <div id="twitter-logo">
                    <img src="static/images/twitter.png" style="width: 50px">
                </div>
            </div>
        </div>

        <div id="member2">
            <div id="person-img">
                <img src="static/images/vidyanshu.jpeg" style="width: 100%; height: 185px;border-radius: 50%">
            </div>
            <div id="person-details">
                <div style="width: 100%;height: fit-content; text-align: center;
                margin-top: 40px">
                    <b style="color: #20509E; font:bold 18px Arial, Helvetica, sans-serif">
                        VIDYANSHU MISHRA
                    </b><br><br>
                    <b style="color: #20509E; font: 16px Arial, Helvetica, sans-serif">
                        Database Manager
                    </b>
                </div>
            </div>
            <div id="social-symbols">
                <div id="insta-logo">
                    <img src="static/images/insta.png" style="width: 50px">
                </div>
                <div id="linkdin-logo">
                    <img src="static/images/linkdin.png" style="width: 50px">
                </div>
                <div id="twitter-logo">
                    <img src="static/images/twitter.png" style="width: 50px">
                </div>
            </div>
        </div>

        <div id="member3">
            <div id="person-img">
                <img src="static/images/abhishek.jpeg" style="width: 100%; height: 185px;border-radius: 50%">
            </div>
            <div id="person-details">
                <div style="width: 100%;height: fit-content; text-align: center;
                margin-top: 40px">
                    <b style="color: #20509E; font:bold 18px Arial, Helvetica, sans-serif">
                        ABHISHEK JAIDEEP
                    </b><br><br>
                    <b style="color: #20509E; font: 16px Arial, Helvetica, sans-serif">
                        UI Designer
                    </b>
                </div>
            </div>
            <div id="social-symbols">
                <div id="insta-logo">
                    <img src="static/images/insta.png" style="width: 50px">
                </div>
                <div id="linkdin-logo">
                    <img src="static/images/linkdin.png" style="width: 50px">
                </div>
                <div id="twitter-logo">
                    <img src="static/images/twitter.png" style="width: 50px">
                </div>
            </div>
        </div>

        <div id="member4">
            <div id="person-img">
                <img src="static/images/akshay.jpeg" style="width: 100%; height: 185px;border-radius: 50%">
            </div>
            <div id="person-details">
                <div style="width: 100%;height: fit-content; text-align: center;
                margin-top: 40px">
                    <b style="color: #20509E; font:bold 18px Arial, Helvetica, sans-serif">
                        AKSHAY MISHRA
                    </b><br><br>
                    <b style="color: #20509E; font: 16px Arial, Helvetica, sans-serif">
                        Full Stack Developer
                    </b>
                </div>
            </div>
            <div id="social-symbols">
                <div id="insta-logo">
                    <img src="static/images/insta.png" style="width: 50px">
                </div>
                <div id="linkdin-logo">
                    <img src="static/images/linkdin.png" style="width: 50px">
                </div>
                <div id="twitter-logo">
                    <img src="static/images/twitter.png" style="width: 50px">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

