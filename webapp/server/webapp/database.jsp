<html>
<head>
    <style>
        body {
            margin: 0;
            background-color: #F1F4F6;
            overflow-x: hidden;
            overflow-y: hidden;
        }

        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 150px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0, 0, 0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
        }

        /* Modal Content */
        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 35%;
            height: 80px;
        }

        #header {
            height: 10%;
            width: 100%;
            background-color: #E6E6E6;
            box-shadow: 5px 5px 10px rgba(122, 122, 122, 0.63);
            position: fixed;
        }

        #lower_body {
            height: 100%;
            width: 100%;
        }

        #company_name {
            float: left;
            width: 100px;
            padding-top: 22px;
            padding-left: 60px;
            color: #20509E;
            font: bold 25px Arial, Helvetica, sans-serif;
        }

        #side-nav {
            width: 20%;
            height: 100%;
            background-color: white;
            box-shadow: 5px 5px 10px rgba(122, 122, 122, 0.63);
            float: left;
        }

        #form-area {
            width: 78%;
            height: 100%;
            float: right;
        }

        #people-img {
            height: fit-content;
            width: fit-content;
            float: right;
            margin-top: 5px;
            margin-right: 60px;
        }

        #navbar {
            height: 80%;
            width: 80%;
            margin-top: 100px;
            margin-left: 25px;
        }

        a:link {
            text-decoration: none;
        }

        a:visited {
            text-underline: none;
            color: #20509E;
        }

        #topic-name {
            margin-top: 80px;
            width: 98%;
            height: 12%;
        }

        #database-view {
            width: 98%;
            height: 68%;
            overflow-y: auto;
        }

        #display-data {
            width: 95%;
            height: 60px;
            margin-top: 20px;
            background-color: white;
            margin-left: 35px;
            border-radius: 40px;
            box-shadow: 5px 10px 5px rgba(122, 122, 122, 0.63);
        }

        #input-box-data {
            width: 150px;
            margin-left: 30px;
            margin-top: 10px;
            height: 40px;
            border: none;
            text-align: center;
            color: #20509E;
            background-color: transparent;
            font: bold 15px Arial, Helvetica, sans-serif;
        }
    </style>
</head>
<title>
    Database
</title>
<body>
<div id="header">
    <div id="company_name">
        <b>ShipChain</b>
    </div>
    <div id="people-img">
        <img src="static/images/person.png" style="width: 55px; height: 55px; border-radius: 50%">
    </div>
</div>
<div id="lower_body">
    <div id="side-nav">
        <div id="navbar" style="position: static">
            <%@include file="sidenav.jsp" %>
        </div>
    </div>

    <div id="myModal" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <p>Waiting for response..</p>
        </div>
    </div>

    <div id="form-area">
        <div id="topic-name">
            <br><br>
            <b style="color: #20509E; font: bold 20px Arial, Helvetica, sans-serif; margin-left: 100px">
                Block id</b>
            <b style="color: #20509E; font: bold 20px Arial, Helvetica, sans-serif; margin-left: 120px">
                Time</b>
            <b style="color: #20509E; font: bold 20px Arial, Helvetica, sans-serif;margin-left: 135px">
                Date</b>
            <b style="color: #20509E; font: bold 20px Arial, Helvetica, sans-serif;margin-left: 130px">
                Blocks</b>
        </div>
        <div id="database-view">
            <%for (int i = 0; i < 5; i++) {%>
            <div id="display-data">
                <input disabled id="input-box-data" placeholder="Block Id">
                <input disabled id="input-box-data" placeholder="Time">
                <input disabled id="input-box-data" placeholder="Date">
                <input disabled id="input-box-data" placeholder="Blocks">
                <a href="detailview.jsp" style="text-decoration: none">
                    <button style="width: 80px; height: 30px; background-color: #57B846; border: none;
                        box-shadow: 5px 5px 10px rgba(65, 65, 65, 0.69); border-radius: 20px;
                        font: bold 16px Arial, Helvetica, sans-serif; color: white; margin-left: 40px">View
                    </button>
                </a>
            </div>
            <%}%>
        </div>
    </div>
</div>
</body>

<script src="http://code.jquery.com/jquery-latest.min.js "></script>
<script>

    /*var modal = document.getElementById("myModal");
    var btn = document.getElementById("myBtn");
    var jsonString = document.cookie.split("=");

    if(jsonString[1]==='true'){
        console.log("if");
        modal.style.display="block";
        var response = $.get('/get-keys');
        response.success(function (result) {
            modal.style.display = "none";
            var obj = jQuery.parseJSON(result);
            alert("Setting Keys");
            document.getElementById("pc-key").innerText = obj.PC;
            document.getElementById("server-key").innerText = obj.Server;
            document.getElementById("connectBtn").disabled = true;
            document.getElementById("connectBtn").style.backgroundColor = "grey";
        });
        response.error(function (jqXHR, textStatus, errorThrown) {
            modal.style.display = "none";
            alert("Server error...cannot display keys now")
        })
    }

    function connectToServer() {
        modal.style.display = "block";
        console.log("called");
        var response = $.get('/connect-device');
        response.success(function (result) {
            modal.style.display = "none";
            var obj = jQuery.parseJSON(result);
            alert("Setting Keys");
            document.getElementById("pc-key").innerText = obj.PC;
            document.getElementById("server-key").innerText = obj.Server;
            document.getElementById("connectBtn").disabled = true;
            document.getElementById("connectBtn").style.backgroundColor = "grey";
        });
        response.error(function (jqXHR, textStatus, errorThrown) {
            modal.style.display = "none";
            alert("Server error...pls wait")
        })
    }*/
</script>
</html>
