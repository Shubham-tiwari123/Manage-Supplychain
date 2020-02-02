<html>
<head>
    <style>
        body {
            margin: 0;
            background-image: url("static/images/bg.png");
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
        }

        #company_name {
            width: 20%;
            height: fit-content;
            padding-top: 22px;
            padding-left: 60px;
            float: left;
            color: #20509E;
            font: bold 25px Arial, Helvetica, sans-serif;
        }

        #top-navbar {
            width: 70%;
            height: fit-content;
            padding-top: 22px;
            padding-right: 60px;
            float: right;
            color: white;
            text-align: right;
            font: bold 18px Arial, Helvetica, sans-serif;
        }

        #quotes {
            float: left;
            width: 50%;
            height: 100%;
        }

        #login_form {
            float: right;
            width: 50%;
            height: 100%;
        }

        #form {
            height: 70%;
            width: 45%;
            background-color: white;
            margin-left: 222px;
            margin-top: 38px;
            border-radius: 25px;
            box-shadow: 15px 15px 10px rgba(65, 65, 65, 0.69);
        }

        #line1 {
            color: #20509E;
            font: bold 40px Arial, Helvetica, sans-serif;
            margin-top: 135px;
            margin-left: 60px;
        }

        #line2 {
            color: #20509E;
            font: 22px Arial, Helvetica, sans-serif;
            margin-top: 15px;
            margin-left: 60px;
        }

        #block_logo {
            height: 35%;
            width: 100%;
        }

        #user_input {
            height: 60%;
            width: 100%;
        }

        #logo {
            height: 100%;
            width: 120px;
        }

        #form_fill {
            width: 240px;
            margin-top: 25px;
            border-radius: 10px;
            height: 35px;
            background-color: #d6d6d6;
            border: none;
            box-shadow: 5px 5px 10px rgba(65, 65, 65, 0.69);
        }

        #submitBtn {
            margin-left: 65px;
            margin-top: 30px;
            width: 100px;
            height: 30px;
            border-radius: 10px;
            background-color: #57B846;
            border: none;
            color: white;
            font: bold 16px Arial, Helvetica, sans-serif;
            box-shadow: 5px 5px 10px rgba(65, 65, 65, 0.69);
        }
    </style>
</head>
<title>
    ShipChain
</title>
<body>
<div id="header">
    <div id="company_name" >
        <b>ShipChain</b>
    </div>
    <div id="top-navbar">
        <b style="margin-right: 20px">HOME</b>
        <b style="margin-right: 20px"><a href="#" style="color: inherit;
        text-decoration: none">ABOUT</a></b>
        <b style="margin-right: 20px"><a href="team.jsp" style="color: inherit;
        text-decoration: none">TEAM</a></b>
        <button style="background-color: transparent; height: 35px; width: 100px; border-radius: 80px;
        color: white; font: bold 15px Arial, Helvetica, sans-serif; border-color:white;
        border-width: 3px">
         SIGN UP
        </button>
    </div>
</div>
<div id="lower_body">
    <div id="quotes">
        <div id="line1">
            Let's Build the Future<br>Together
        </div>
        <div id="line2">
            We help business leaders to easily adopt blockchain<br>
            technology and grow their business
        </div>
    </div>
    <div id="login_form">
        <div id="form">
            <div id="block_logo" align="center">
                <div id="logo">
                    <img src="static/images/logo2.jpg" style="margin-top: 35px; height: 100px">
                </div>
            </div>
            <div id="user_input" style="margin-left: 22px">
                <div id="form_fill">
                    <img src="static/images/email.png" style="float: left; height:18px; width: 18px;
                        margin-top: 8px; margin-left: 8px">
                    <input type="text" id="useremail" placeholder="Email" name="userEmail" style=" width: 200px;
                        height: 100%;border: none; background-color: transparent;float: right;
                        margin-right: 10px;color: #464646; font: bold 16px Arial, Helvetica, sans-serif;">
                </div>

                <div id="form_fill">
                    <img src="static/images/pass.png" style="float: left; height:18px; width: 18px;
                        margin-top: 8px; margin-left: 8px">
                    <input type="password" id="pass" placeholder="Password" style=" width: 200px; height: 100%;
                        border: none; background-color: transparent;float: right; margin-right: 10px;
                        color: #464646; font: bold 16px Arial, Helvetica, sans-serif;" name="userPass">
                </div>

                <a href="dashboard.jsp" style="color: inherit;
        text-decoration: none"><button id="submitBtn">LOGIN</button></a>
            </div>
        </div>
    </div>
</div>

<script src="http://code.jquery.com/jquery-latest.min.js "></script>
<script>

    function loginUser() {
        console.log(document.getElementById("useremail").value);
        console.log(document.getElementById("pass").value);

        var response = $.post('/login-user',{
            email:document.getElementById("useremail").value,
            pass:document.getElementById("pass").value
        });

        response.success(function (result) {
            window.location.replace('/dashboard');
        });

        response.error(function (jqXHR, textStatus, errorThrown) {
            alert("Server error...pls wait")
        })
    }
</script>
</body>
</html>
