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
        #prepare-block{
            width: 50%;
            height: 75%;
            margin-top: 120px;
            background-color: white;
            border-radius: 25px;
            margin-left: 250px;
            box-shadow: 5px 5px 10px rgba(122, 122, 122, 0.63);
        }
        #getBlockID{
            margin-top: 100px;
            float: right;
            height: 32px;
            width: 90px;
            border-radius: 20px;
            margin-right: 80px;
            background-color: #57B846;
            border: none;
            color: white;
            font: bold 15px Arial, Helvetica, sans-serif;
            box-shadow: 5px 5px 10px rgba(65, 65, 65, 0.69);
        }
        #caption{
            width: fit-content;
            height: fit-content;
            float: left;
            margin-top: 10px ;
            margin-left: 50px;
        }
        #caption p{
            color: #20509E;
            font: bold 20px Arial, Helvetica, sans-serif;

        }
        #input-box{
            width: fit-content;
            height: fit-content;
            float: right;
            margin-right: 38px;
            margin-top: 10px;
        }
        #input-box input,select{
            margin-top: 38px;
            height: 35px;
            width: 75%;
            margin-right: 40px;
            border-radius: 10px;
            background-color: #d6d6d6;
            border: none;
            text-align: center;
            font-family: Arial, Helvetica, sans-serif;
        }
        select{
            margin-right: 68px;
            -moz-appearance:none; /* Firefox */
            -webkit-appearance:none; /
        }
        #send-btn{
            margin-top: 30px;
            height: 32px;
            width: 90px;
            border-radius: 20px;
            margin-right: 80px;
            background-color: #57B846;
            border: none;
            color: white;
            font: bold 15px Arial, Helvetica, sans-serif;
            box-shadow: 5px 5px 10px rgba(65, 65, 65, 0.69);
        }
        select.minimal {
            background-image:url("/static/images/dropdown.png");
            background-position-x: 95%;
            background-position-y: 40%;
            background-size:
                    20px 20px,
                    25px 25px,
                    21px 3.5em;
            background-repeat: no-repeat;
        }

        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        /* Firefox */
        input[type=number] {
            -moz-appearance:textfield;
        }

    </style>
</head>
<title>
    ShipChain
</title>
<body>
<div id="header">
    <div id="company_name">
        <b>ShipChain</b>
    </div>
    <div id="people-img">
        <img src="static/images/person.png" style="width: 55px; height: 55px">
    </div>
</div>
<div id="lower_body">
    <div id="side-nav">
        <div id="navbar" style="position: static">
            <div id="dashboard-area" style="margin-top: 150px; height: fit-content;
             width: fit-content">
                <img src="/static/images/dashboard.png" style="width: 35px;height: 35px; float: left; margin-left: 17px;
                margin-top: 13px">
                <p style="float: right; margin-right: 50px;font: bold 19px Arial, Helvetica, sans-serif;
            color: #20509E;"><a href="/dashboard" method="get">Dashboard</a></p>
            </div>
            <div id="block-area" style="margin-top: 50px; height: fit-content;
             width: fit-content">
                <img src="/static/images/prepareblock.png  " style="width: 35px;height: 35px; float: left; margin-left: 17px;
                margin-top: 27px">
                <p style="float: right; margin-right: 20px;font: bold 19px Arial, Helvetica, sans-serif;
            color: #20509E;"><a href="/prepare-block" method="get">Prepare Block</a>
            </div>
            <div id="logout-area" style="margin-top: 50px; height: fit-content;
             width: fit-content">
                <img src="/static/images/logout.png" style="width: 35px;height: 35px; float: left; margin-left: 17px;
                margin-top: 25px">
                <p style="float: right; margin-right: 80px;font: bold 19px Arial, Helvetica, sans-serif;
            color: #20509E;"><a href="/">Logout</a></p>
            </div>
        </div>
    </div>
    <div id="form-area">

        <div id="myModal" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <p>Waiting for response..</p>
            </div>
        </div>

        <button id="getBlockID" onclick="getBlockID()">GET ID</button>
        <div id="prepare-block">
            <form>
            <div id="caption">
                <p style="margin-top: 48px;">Block ID</p>
                <p style="margin-top: 48px">Product Name</p>
                <p style="margin-top: 48px">Quantity</p>
                <p style="margin-top: 48px">Suplier Name</p>
                <p style="margin-top: 48px">Price</p>
            </div>
            <div id="input-box">
                <input id="setBlockID" disabled><br>
                <select class="minimal">
                    <option>Product Name</option>
                    <option>Product Name</option>
                    <option>Product Name</option>
                    <option>Product Name</option>
                </select><br>
                <input type="number"><br>
                <select class="minimal">
                    <option>Suplier Name</option>
                    <option>Suplier Name</option>
                    <option>Suplier Name</option>
                    <option>Suplier Name</option>
                </select><br>
                <input type="number" ><br>
            </div>
                <button type="submit" id="send-btn">SEND</button>
            </form>
        </div>
    </div>
</div>
</body>
<script src="http://code.jquery.com/jquery-latest.min.js "></script>
<script>

    var modal = document.getElementById("myModal");
    var btn = document.getElementById("myBtn");

    document.getElementById("setBlockID").value = ""
    function getBlockID() {
        modal.style.display = "block";
        console.log("called");
        var response = $.get('/get-id');
        response.success(function (result) {
            modal.style.display = "none";
            alert(result)
            document.getElementById("setBlockID").value = result;
            document.getElementById("setBlockID").disabled = true
        })
        response.error(function (jqXHR, textStatus, errorThrown) {
            modal.style.display = "none";
            alert("Server error...pls wait")
        })
    }
</script>
</html>
