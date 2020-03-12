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
            height: 65%;
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
        <img src="static/images/person.png" style="width: 45px; height: 45px; border-radius: 50%;
        border: 1px solid grey; margin-top: 5px">
    </div>
</div>
<div id="lower_body">
    <div id="side-nav">
        <div id="navbar" style="position: static">
            <%@include file="sidenav.jsp"%>
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
            <div id="caption">
                <p style="margin-top: 48px;">Block ID</p>
                <p style="margin-top: 48px">Quantity</p>
                <p style="margin-top: 48px">Total Boxes</p>
                <p style="margin-top: 48px;">Box Numbers</p>
            </div>
            <div id="input-box">
                <input id="setBlockID" disabled required><br>
                <input id="qunt" type="number" max="100" min="10" required><br>
                <input id="total-boxes" type="number" max="100" min="10" required><br>
                <input type="text" id="box-number" placeholder="Eg: 10-100" required><br>
            </div>
                <button type="submit" style="margin-left: 15px"
                        onclick="sendBlockData()" id="send-btn">SEND</button>
        </div>
    </div>
</div>
</body>
<script src="http://code.jquery.com/jquery-latest.min.js "></script>
<script>

    var modal = document.getElementById("myModal");
    var btn = document.getElementById("myBtn");

    document.getElementById("setBlockID").value = "";
    function getBlockID() {
        modal.style.display = "block";
        console.log("called");
        var response = $.get('/get-id');
        response.success(function (result) {
            modal.style.display = "none";
            alert(result);
            document.getElementById("setBlockID").value = result;
            document.getElementById("getBlockID").disabled = true;
            document.getElementById("getBlockID").style.backgroundColor = "grey";
        });
        response.error(function (jqXHR, textStatus, errorThrown) {
            modal.style.display = "none";
            alert("Server error...pls wait")
        })
    }
    
    function sendBlockData() {
        var blockId = document.getElementById("setBlockID").value;
        var qunt = document.getElementById("qunt").value;
        var totalBoxes = document.getElementById("total-boxes").value;
        var boxNumbers = document.getElementById("box-number").value;

        console.log("block",blockId,qunt,totalBoxes,boxNumbers);
        if((blockId.length===0) || (qunt.length===0) || (totalBoxes.length===0) || (boxNumbers.length==0)){
            console.log("if");
            alert("Fill the form")
        }
        else{
            modal.style.display = "block";
            console.log("else");
            var response = $.post('/send-block3',{
                productQun:qunt,
                blockID:blockId,
                totalBoxes:totalBoxes,
                boxNumber:boxNumbers
            });

            response.success(function (result) {
                if(result==200) {
                    modal.style.display = "none";
                    alert("Data send");
                    document.getElementById("setBlockID").value = " ";
                    document.getElementById("qunt").value = "";
                    document.getElementById("qunt").style.borderColor = "none";
                    document.getElementById("total-boxes").value = 0;
                    document.getElementById("box-number").value = 0;
                    document.getElementById("getBlockID").disabled = false;
                    document.getElementById("getBlockID").style.backgroundColor = "#57B846";
                }else{
                    for (var i=0;i<30;i++);
                    modal.style.display = "none";
                    alert("Some error occurred...pls try again")
                }
            });

            response.error(function (jqXHR, textStatus, errorThrown) {
                for (var i=0;i<30;i++);
                modal.style.display = "none";
                alert("Server error...pls wait");
            })
        }

    }
</script>
</html>
