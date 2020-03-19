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
            width: 100px;
            padding-top: 22px;
            padding-left: 60px;
            color: #20509E;
            font: bold 25px Arial, Helvetica, sans-serif;
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
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            padding-top: 14%;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.2);
        }

        /* Modal Content */
        .modal-content {
            background-color: white;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 25%;
            height: 120px;
            border-radius: 20px;
        }

        #popup-text{
            font:bold 24px Arial, Helvetica, sans-serif;
            color: rgba(92, 93, 94, 0.78);
            text-align: center;
            width: 100%;
            height: fit-content;
            margin-top: 30px;
        }

        #confirm-btn{
            background-color: #57B846;
            border: #57B846;
            color: white;
            margin-left: 40%;
            height: 30px;
            width: 60px;
            border-radius: 40px;
            font:bold 16px Arial, Helvetica, sans-serif;
            box-shadow: 5px 5px 10px rgba(65, 65, 65, 0.69);
        }
    </style>
    <script>
        var x = document.cookie;
        let cookies = x.split(";");
        console.log("cookie",cookies);
        for(var i=0;i<cookies.length;i++){
            if (cookies[i]===" loginStatus=true" || cookies[i]==="loginStatus=true"){
                window.location.replace("/dashboard")
            }
        }
    </script>
</head>
<title>
    ShipChain
</title>
<body>
<div id="popup-msg" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <p id="popup-text" >Patient ID: 58452945</p>
        <button id="confirm-btn">OK</button>
    </div>
</div>
<div id="header">
    <div id="company_name">
        <b>ShipChain</b>
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
                        <input type="text" id="userEmail" placeholder="Email" name="userEmail" style=" width: 200px;
                        height: 100%;border: none; background-color: transparent;float: right;
                        margin-right: 10px;color: #464646; font: bold 16px Arial, Helvetica, sans-serif;">
                    </div>

                    <div id="form_fill">
                        <img src="static/images/pass.png" style="float: left; height:18px; width: 18px;
                        margin-top: 8px; margin-left: 8px">
                        <input type="password" id="userPass" placeholder="Password" style=" width: 200px; height: 100%;
                        border: none; background-color: transparent;float: right; margin-right: 10px;
                        color: #464646; font: bold 16px Arial, Helvetica, sans-serif;" name="userPass">
                    </div>

                    <button onclick="sendResponse()" id="submitBtn">LOGIN</button>
            </div>
        </div>
    </div>
</div>

<script src="http://code.jquery.com/jquery-latest.min.js "></script>
<script>

    let modal = document.getElementById("popup-msg");
    let conform_btn = document.getElementById("confirm-btn");

    conform_btn.onclick = function(){
        document.getElementById("popup-text").style.color = "rgba(92, 93, 94, 0.78)";
        modal.style.display = "none";
    };

    window.onload = function(){
        console.log("Loading window");
        document.getElementById("userEmail").value = "";
        document.getElementById("userPass").value = "";
    };

    async function sendResponse() {
        const email = document.getElementById("userEmail").value;
        const pass = document.getElementById("userPass").value;

        let flag = await validateForm(email,pass);

        if(flag) {
            modal.style.display = "block";
            document.getElementById("popup-text").innerText = "Checking credentials...";
            document.getElementById("confirm-btn").style.visibility="hidden";

            console.log("values:",email,pass);

            let response = $.post('/login', {
                email: email,
                pass: pass
            });

            setTimeout(function () {
                response.success(function (result) {
                    document.getElementById("userEmail").value = "";
                    document.getElementById("userPass").value = "";
                    const resultObj = jQuery.parseJSON(result);
                    if(resultObj.statusCode===200){
                        // forward to login page
                        console.log("iffff");
                        window.location.replace('/dashboard');
                    }else{
                        document.getElementById("popup-text").innerText = "Wrong credentials";
                        document.getElementById("popup-text").style.color = "#BA0606";
                        document.getElementById("confirm-btn").style.visibility ="visible";
                    }

                });

                response.error(function (jqXHR, textStatus, errorThrown) {
                    document.getElementById("popup-text").innerText = "Server Error";
                    document.getElementById("popup-text").style.color = "#BA0606";
                    document.getElementById("confirm-btn").style.visibility ="visible";
                })
            }, 2000);

        }
    }


    function validateForm(email,pass) {
        console.log("validating form");
        if(email===""){
            alert("Please provide email");
            document.getElementById("userEmail").focus();
            return false;
        }
        if(email!==""){
            let atpos = email.indexOf("@");
            let dotpos = email.indexOf(".");
            if(atpos<1 || (dotpos-atpos)<2){
                alert("Please provide valid email");
                document.getElementById("userEmail").focus();
                return false;
            }
        }
        if(pass ===""){
            alert("Please provide password");
            document.getElementById("userPass").focus();
            return false;
        }
        return true;
    }
</script>

</body>
</html>
