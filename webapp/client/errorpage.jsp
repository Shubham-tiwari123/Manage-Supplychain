<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page Not Found</title>
    <style>
        body{
            margin: 0;
            padding: 0;
            overflow: hidden;
        }
        #error_page{
            overflow: hidden;
            height: 100vh;
            width: 100%;
            background-image: url("static/images/error.jpg");
            background-repeat: no-repeat;
            background-size: 100% 95%;
        }
        #go-back{
            position: relative;
            width:140px;
            top:85%;
            left:46%;
            height: 40px;
            font-size: 20px;
            color: white;
            background-color: #4D3F8C;
            border: none;
            border-radius: 20px;
        }
    </style>
</head>
<body>
<div id="error_page">
    <button id="go-back" onclick="goBtn()"><b>GO BACK</b></button>
</div>
</body>
<script>
    function goBtn() {
        window.location.replace("/dashboard")
    }
</script>
</html>
