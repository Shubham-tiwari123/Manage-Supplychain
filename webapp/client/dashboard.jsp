<html>
<head>
    <style>
        body {
            margin: 0;
            background-color: #F1F4F6;
            overflow-x: hidden;
            overflow-y: hidden;
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

        #pc-img {
            width: fit-content;
            height: fit-content;
            margin-top: 100px;
            margin-left: 80px;
            float: left;
        }

        #client-img {
            width: fit-content;
            height: fit-content;
            margin-top: 140px;
            margin-right: 120px;
            float: right;
        }

        #connectBtn {
            position: absolute;
            height: 40px;
            width: 130px;
            bottom: 0;
            border-radius: 20px;
            margin-bottom: 380px;
            margin-left: 80px;
            background-color: #57B846;
            border: none;
            color: white;
            font: bold 18px Arial, Helvetica, sans-serif;
            box-shadow: 5px 5px 10px rgba(65, 65, 65, 0.69);
        }

        #server-cap {
            font: bold 18px Arial, Helvetica, sans-serif;
            color: #20509E;
            margin-left: 30%
        }

        #client-cap {
            font: bold 18px Arial, Helvetica, sans-serif;
            color: #20509E;
            margin-left: 35%;
            margin-top: 30px;
        }

        #table-style {
            margin-top: 400px;
            width: 90%;
            height: fit-content;
            margin-left: 40px;
            border-radius: 25px;
            background-color: white;
            box-shadow: 5px 5px 10px rgba(65, 65, 65, 0.69);
        }

        #public-keys {
            border-collapse: collapse;
            width: 100%;
        }

        #public-keys th {
            text-align: center;
            background-color: #4CAF50;
            color: white;
            height: 30px;
            font: bold 17px Arial, Helvetica, sans-serif;
        }

        #public-keys td, #public-keys th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #public-keys td {
            text-align: center;
            font: bold 17px Arial, Helvetica, sans-serif;
            color: #20509E;
        }

        #public-keys tr:nth-child(odd) {
            background-color: #E6E6E6
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
            background-color: rgb(0, 0, 0);
            background-color: rgba(0, 0, 0, 0.2);
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

        #popup-text {
            font: bold 24px Arial, Helvetica, sans-serif;
            color: rgba(92, 93, 94, 0.78);
            text-align: center;
            width: 100%;
            height: fit-content;
            margin-top: 30px;
        }

        #confirm-btn {
            background-color: #57B846;
            border: #57B846;
            color: white;
            margin-left: 40%;
            height: 30px;
            width: 60px;
            border-radius: 40px;
            font: bold 16px Arial, Helvetica, sans-serif;
            box-shadow: 5px 5px 10px rgba(65, 65, 65, 0.69);
        }
    </style>
</head>
<body>
<div id="popup-msg" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <p id="popup-text">Patient ID: 58452945</p>
        <button id="confirm-btn">OK</button>
    </div>
</div>
<div id="header">
    <div id="company_name">
        <b>ShipChain</b>
    </div>
    <div id="people-img">
        <img src="static/images/person.png" style="width: 45px; height: 45px; border-radius: 50%;
        border: 1px solid grey; margin-top: 5px">
    </div>
</div>
<div id="lower_body">
    <div id="side-nav">
        <div id="navbar" style="position: static">
            <%@include file="sidenav.jsp" %>
        </div>
    </div>

    <div id="form-area">
        <div id="pc-img">
            <img src="static/images/server.png" style="width: 280px; height: 250px">
            <div id="server-cap">SERVER</div>
        </div>
        <div id="client-img">
            <img src="static/images/client.png" style="width: 220px; height: 180px">
            <div id="client-cap">CLIENT</div>
        </div>
        <button id="connectBtn" onclick="connectToServer()">CONNECT</button>
        <div id="table-style">
            <table id="public-keys">
                <tr>
                    <th style="border-radius: 16px 0px 0px 0px; width: 15%">Device</th>
                    <th style="border-radius: 0px 16px 0px 0px;width: 85%">Public Keys</th>
                </tr>
                <tr>
                    <td>PC</td>
                    <td id="pc-key"></td>
                </tr>
                <tr>
                    <td style="border-radius: 0px 0px 16px 16px">Server</td>
                    <td id = "server-key" style="border-radius: 0px 0px 16px 16px">
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>

<script src="http://code.jquery.com/jquery-latest.min.js "></script>
<script>

    let modal = document.getElementById("popup-msg");
    document.getElementById("popup-text").innerText = "Getting Keys...";
    document.getElementById("confirm-btn").style.visibility="hidden";
    let conform_btn = document.getElementById("confirm-btn");

    setTimeout(function () {
        modal.style.display = "block";
    },500);

    conform_btn.onclick = function(){
        document.getElementById("popup-text").style.color = "rgba(92, 93, 94, 0.78)";
        modal.style.display = "none";

    };

    let response = $.get('/get-keys');

    setTimeout(function () {
        response.success(function (result) {
            let obj = jQuery.parseJSON(result);
            console.log("obj: hett keys:",obj);
            if(obj.statusCode===200) {
                document.getElementById("popup-text").innerText = "Setting Keys";
                document.getElementById("pc-key").innerText = obj.PC;
                document.getElementById("server-key").innerText = obj.Server;
                document.getElementById("connectBtn").disabled = true;
                document.getElementById("connectBtn").style.backgroundColor = "grey";
                setTimeout(function () {
                    modal.style.display = "none";
                },1500);
            }else{
                document.getElementById("popup-text").innerText = "DB error... refresh page";
                document.getElementById("popup-text").style.color = "#BA0606";
                document.getElementById("confirm-btn").style.visibility ="visible";
            }
        });

        response.error(function (jqXHR, textStatus, errorThrown) {
            document.getElementById("popup-text").innerText = "Server error... refresh page";
            document.getElementById("popup-text").style.color = "#BA0606";
            document.getElementById("confirm-btn").style.visibility ="visible";
        })
    },3000);


    function connectToServer() {
        modal.style.display = "block";
        document.getElementById("popup-text").innerText = "Connecting to server";
        document.getElementById("confirm-btn").style.visibility="hidden";
        console.log("called");
        let res = $.get('/connect-server');

        setTimeout(function () {
            res.success(function (result) {
                var obj = jQuery.parseJSON(result);
                document.getElementById("popup-text").innerText = "Setting keys";
                document.getElementById("pc-key").innerText = obj.PC;
                document.getElementById("server-key").innerText = obj.Server;
                document.getElementById("connectBtn").disabled = true;
                document.getElementById("connectBtn").style.backgroundColor = "grey";
                setTimeout(function () {
                    modal.style.display = "none";
                },1000);
            });
            res.error(function (jqXHR, textStatus, errorThrown) {
                document.getElementById("popup-text").innerText = "Server error... try again";
                document.getElementById("popup-text").style.color = "#BA0606";
                document.getElementById("confirm-btn").style.visibility ="visible";
            })

        },3000)
    }
</script>
</html>
