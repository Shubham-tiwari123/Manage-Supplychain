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
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
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

        #detail-view{
            width: 97%;
            height: 80%;
            margin-top: 100px;
            border-radius: 40px;
        }

        #block-details{
            width: 96%;
            height: 100px;
            margin-left: 15px;
        }

        #block1,#block2,#block3,#block4{
            height: 63%;
            width: 23%;
            background-color: white;
            float: left;
            margin-left: 15px;
            margin-top: 50px;
            border-radius: 20px;
            box-shadow: 10px 10px 10px rgba(122, 122, 122, 0.63);
        }

        .input-value{
            border-radius: 5px;
            border: none;
            height: 25px;
            background-color: #d5d2d2;
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

    <div id="myModal" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <p>Waiting for response..</p>
        </div>
    </div>

    <div id="form-area">
        <div id="detail-view">
            <div id="block-details">
                <br><b style="color: #20509E;font: bold 18px Arial, Helvetica, sans-serif;
                margin-left:  20px">Block ID:</b>
                <input disabled style="width: 180px; margin-left: 65px; height:30px;background-color: white;
                box-shadow: 5px 5px 10px rgba(122, 122, 122, 0.63);border: none; border-radius: 10px">
                <b style="color: #20509E;font: bold 18px Arial, Helvetica, sans-serif;
                margin-left:  250px">Creation Date:</b>
                <input disabled style="width: 180px; margin-left: 20px; height:30px;background-color: white;
                box-shadow: 5px 5px 10px rgba(122, 122, 122, 0.63);border: none; border-radius: 10px"><br><br>
                <b style="color: #20509E;font: bold 18px Arial, Helvetica, sans-serif;
                margin-left:  20px">Creation Time:</b>
                <input disabled style="width: 180px; margin-left: 20px; height:30px;background-color: white;
                box-shadow: 5px 5px 10px rgba(122, 122, 122, 0.63);border: none; border-radius: 10px">
            </div>
            <div id="block1">
                <br>
                <b style="color: #20509E; font: bold 22px Arial, Helvetica, sans-serif; margin-left: 35%;">Block 1</b>
                <div id="block-data" style=" margin-top: 30px; padding-left: 15px;
                color: #20509E; font: bold 17px Arial, Helvetica, sans-serif;">
                    <b>Item:</b>
                    <input class="input-value" disabled style="width: 148px; margin-left: 12px;"><br><br>
                    <b>Quantity:</b>
                    <input class="input-value" disabled style="width: 115px; margin-left: 10px;"><br><br>
                    <b>Price:</b>
                    <input class="input-value" disabled style="width: 145px; margin-left: 8px;"><br><br>
                    <b>Supplier Name:</b>
                    <input class="input-value" disabled style="width: 200px; margin-top: 10px;">
                </div>
            </div>
            <div id="block2">
                <br>
                <b style="color: #20509E; font: bold 22px Arial, Helvetica, sans-serif; margin-left: 35%;">Block 2</b>
                <div id="block-data" style=" margin-top: 30px; padding-left: 15px;
                color: #20509E; font: bold 17px Arial, Helvetica, sans-serif;">
                    <b>Item:</b>
                    <input class="input-value" disabled style="width: 148px; margin-left: 12px;"><br><br>
                    <b>Quantity:</b>
                    <input class="input-value" disabled style="width: 115px; margin-left: 10px;"><br><br>
                    <b>Temp:</b>
                    <input class="input-value" disabled style="width: 145px; margin-left: 8px;"><br><br>
                    <b>Machine Num:</b>
                    <input class="input-value" disabled style="width: 200px; margin-top: 10px;">
                </div>
            </div>
            <div id="block3">
                <br>
                <b style="color: #20509E; font: bold 22px Arial, Helvetica, sans-serif; margin-left: 35%;">Block 3</b>
                <div id="block-data" style=" margin-top: 30px; padding-left: 15px;
                color: #20509E; font: bold 17px Arial, Helvetica, sans-serif;">
                    <b>Item:</b>
                    <input class="input-value" disabled style="width: 148px; margin-left: 12px;"><br><br>
                    <b>Quantity:</b>
                    <input class="input-value" disabled style="width: 115px; margin-left: 10px;"><br><br>
                    <b>Total Box:</b>
                    <input class="input-value" disabled style="width: 105px; margin-left: 8px;"><br><br>
                    <b>Box Ranges:</b>
                    <input class="input-value" disabled style="width: 200px; margin-top: 10px;">
                </div>
            </div>
            <div id="block4">
                <br>
                <b style="color: #20509E; font: bold 22px Arial, Helvetica, sans-serif; margin-left: 35%;">Block 4</b>
                <div id="block-data" style=" margin-top: 30px; padding-left: 15px;
                color: #20509E; font: bold 17px Arial, Helvetica, sans-serif;">
                    <b>Item:</b>
                    <input class="input-value" disabled style="width: 148px; margin-left: 12px;"><br><br>
                    <b>Total Carton:</b>
                    <input class="input-value" disabled style="width: 90px; margin-left: 4px;"><br><br>
                    <b>Carton Ranges:</b>
                    <input class="input-value" disabled style="width: 200px; margin-top: 3px"><br><br>
                    <b>Exporter Name:</b>
                    <input class="input-value" disabled style="width: 200px; margin-top: 5px;">
                </div>
            </div>
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
