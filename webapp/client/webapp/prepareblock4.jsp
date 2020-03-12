<html>
<head>
    <title>
        Prepare block
    </title>
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
            <%@include file="sidenav.jsp"%>
        </div>
    </div>
    <div id="form-area">
        <button id="getBlockID" onclick="getBlockID()">GET ID</button>
        <div id="prepare-block">
            <div id="caption">
                <p style="margin-top: 48px;">Block ID</p>
                <p style="margin-top: 48px">Total Carton</p>
                <p style="margin-top: 48px;">Carton Numbers</p>
                <p style="margin-top: 48px">Exporters</p>
            </div>
            <div id="input-box">
                <input id="setBlockID" disabled required><br>
                <input id="total-cartoon" type="number" required><br>
                <input type="text" id="cartoon-number" placeholder="Eg: 10-100" required><br>
                <select class="minimal" id="exporter-name">
                    <option value="0">Select exporter</option>
                    <option>Exporter Name</option>
                    <option>Exporter Name</option>
                    <option>Exporter Name</option>
                    <option>Exporter Name</option>
                </select><br>
            </div>
                <button type="submit" onclick="sendBlockData()" id="send-btn">SEND</button>
        </div>
    </div>
</div>
</body>
<script src="http://code.jquery.com/jquery-latest.min.js "></script>
<script>

    let modal = document.getElementById("popup-msg");
    let btn = document.getElementById("confirm-btn");

    document.getElementById("setBlockID").value = "";
    document.getElementById("total-cartoon").value=0;
    document.getElementById("exporter-name").selectedIndex = 0;
    document.getElementById("cartoon-number").value=0;

    let conform_btn = document.getElementById("confirm-btn");

    conform_btn.onclick = function(){
        document.getElementById("popup-text").style.color = "rgba(92, 93, 94, 0.78)";
        modal.style.display = "none";
    };

    function getBlockID() {
        modal.style.display = "block";
        document.getElementById("popup-text").innerText = "Getting product id....";
        conform_btn.style.visibility="hidden";

        console.log("called");
        setTimeout(function () {
            let response = $.get('/get-id');
            response.success(function (result) {
                const resultObj = jQuery.parseJSON(result);
                if (resultObj.statusCode=== 200) {
                    modal.style.display = "none";
                    document.getElementById("setBlockID").value = resultObj.productID;
                    document.getElementById("getBlockID").disabled = true;
                    document.getElementById("getBlockID").style.backgroundColor = "grey";
                }else{
                    document.getElementById("popup-text").innerText = "Server error...try again";
                    document.getElementById("popup-text").style.color = "#BA0606";
                    document.getElementById("confirm-btn").style.visibility ="visible";
                }
            });
            response.error(function (jqXHR, textStatus, errorThrown) {
                document.getElementById("popup-text").innerText = "Server error...try again";
                document.getElementById("popup-text").style.color = "#BA0606";
                document.getElementById("confirm-btn").style.visibility ="visible";
            })
        },1500);
    }
    
    async function sendBlockData() {
        let today = new Date();
        let dd = today.getDate();
        let mm = today.getMonth() + 1; //January is 0!

        let yyyy = today.getFullYear();
        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }
        today = dd + '/' + mm + '/' + yyyy;
        console.log("date:",today);


        let blockId = document.getElementById("setBlockID").value;
        let totalCarton = document.getElementById("total-cartoon").value;
        let cartonNumbers = document.getElementById("cartoon-number").value;
        let exporterName = document.getElementById("exporter-name").value;

        console.log("block",blockId,totalCarton,cartonNumbers,exporterName);

        let flag = await validate(blockId,totalCarton,cartonNumbers,exporterName);

        if(flag) {
            modal.style.display = "block";
            document.getElementById("popup-text").innerText = "Submitting record...";
            document.getElementById("confirm-btn").style.visibility = "hidden";
            console.log("valid");

            let response = $.post('/send-block4',{
                totalCarton:totalCarton,
                blockID:blockId,
                cartonNumbers:cartonNumbers,
                exporterName:exporterName,
                date:today
            });

            setTimeout(function () {
                response.success(function (result) {
                    const resultObj = jQuery.parseJSON(result);
                    if (resultObj.statusCode=== 200) {
                        console.log("iffff");
                        document.getElementById("popup-text").innerText = "Form Submitted";
                        document.getElementById("popup-text").style.color = "#57B846";
                        setTimeout(function () {
                            window.location.replace("/prepare_block2")
                        },1000);
                    }else{
                        document.getElementById("popup-text").innerText = "Server error...try again";
                        document.getElementById("popup-text").style.color = "#BA0606";
                        document.getElementById("confirm-btn").style.visibility ="visible";
                    }
                });

                response.error(function (jqXHR, textStatus, errorThrown) {
                    document.getElementById("popup-text").innerText = "Server error...try again";
                    document.getElementById("popup-text").style.color = "#BA0606";
                    document.getElementById("confirm-btn").style.visibility ="visible";
                })
            },3000);
        }else{
            console.log("not-valid");
        }
    }

    function validate(blockID,totalCarton,cartonNumbers,exporterName){
        console.log("validating form");
        let letters = /^[A-Za-z]+$/;
        let split = cartonNumbers.split("-");
        if(blockID===""){
            alert("Please get the blokID");
            document.getElementById("setBlockID").focus();
            return false;
        }
        if(totalCarton<=0){
            alert("Enter valid total number of cartons");
            document.getElementById("total-cartoon").focus();
            return false;
        }
        if(cartonNumbers.indexOf("-")<=1||cartonNumbers===0||cartonNumbers.indexOf("-")===(cartonNumbers.length-1)||
            Number(split[1])<=Number(split[0])||split.length!==2){
            alert("Please provide valid range");
            document.getElementById("cartoon-number").focus();
            return false;
        }
        if(exporterName==="0"){
            alert("Enter valid exporter name");
            document.getElementById("exporter-name").focus();
            return false;
        }
        return true;
    }
</script>
</html>
