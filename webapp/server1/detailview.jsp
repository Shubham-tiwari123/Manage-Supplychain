<html>
<head>
    <title>Detail View</title>
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
            text-align: center;
            color: #464646;
            font-size: 15px;
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
        .caption-input{
            width: 180px;
            height: 30px;
            background-color: white;
            text-align: center;
            box-shadow: 5px 5px 10px rgba(122, 122, 122, 0.63);
            border: none;
            border-radius: 10px;
            color: #464646;
            font-size: 15px;
        }

    </style>
</head>
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
        <div id="detail-view">
            <div id="block-details">
                <br><b style="color: #20509E;font: bold 18px Arial, Helvetica, sans-serif;
                margin-left:  20px">Product ID:</b>
                <input disabled style="margin-left: 50px;" class="caption-input" id="productID">
                <b style="color: #20509E;font: bold 18px Arial, Helvetica, sans-serif;
                margin-left:  200px">Creation Date:</b>
                <input disabled style="margin-left: 20px;" class="caption-input" id="date"><br><br>
                <b style="color: #20509E;font: bold 18px Arial, Helvetica, sans-serif;
                margin-left:  20px">Creation Time:</b>
                <input disabled style="margin-left: 20px;" class="caption-input" id="time">
            </div>
            <div id="block1" <%--style="visibility: hidden"--%>>
                <br>
                <b style="color: #20509E; font: bold 22px Arial, Helvetica, sans-serif; margin-left: 35%;">Block 1</b>
                <div id="block-data" style=" margin-top: 30px; padding-left: 15px;
                color: #20509E; font: bold 17px Arial, Helvetica, sans-serif;">
                    <b>Item:</b>
                    <input class="input-value" id="itemName1" disabled style="width: 148px; margin-left: 12px;"><br><br>
                    <b>Quantity:</b>
                    <input class="input-value" id="qunt1" disabled style="width: 115px; margin-left: 10px;"><br><br>
                    <b>Price:</b>
                    <input class="input-value" id="price" disabled style="width: 145px; margin-left: 8px;"><br><br>
                    <b>Supplier Name:</b>
                    <input class="input-value" id="supplierName" disabled style="width: 200px; margin-top: 10px;">
                </div>
            </div>
            <div id="block2" <%--style="visibility: hidden"--%>>
                <br>
                <b style="color: #20509E; font: bold 22px Arial, Helvetica, sans-serif; margin-left: 35%;">Block 2</b>
                <div id="block-data" style=" margin-top: 30px; padding-left: 15px;
                color: #20509E; font: bold 17px Arial, Helvetica, sans-serif;">
                    <b>Item:</b>
                    <input class="input-value" id="itemName2" disabled style="width: 148px; margin-left: 12px;"><br><br>
                    <b>Quantity:</b>
                    <input class="input-value" id="qunt2" disabled style="width: 115px; margin-left: 10px;"><br><br>
                    <b>Temp:</b>
                    <input class="input-value" id="temp" disabled style="width: 145px; margin-left: 8px;"><br><br>
                    <b>Machine Num:</b>
                    <input class="input-value" id="machineNO" disabled style="width: 200px; margin-top: 10px;">
                </div>
            </div>
            <div id="block3" <%--style="visibility: hidden"--%>>
                <br>
                <b style="color: #20509E; font: bold 22px Arial, Helvetica, sans-serif; margin-left: 35%;">Block 3</b>
                <div id="block-data" style=" margin-top: 30px; padding-left: 15px;
                color: #20509E; font: bold 17px Arial, Helvetica, sans-serif;">
                    <b>Item:</b>
                    <input class="input-value" id="itemName3" disabled style="width: 148px; margin-left: 12px;"><br><br>
                    <b>Quantity:</b>
                    <input class="input-value" id="qunt3" disabled style="width: 115px; margin-left: 10px;"><br><br>
                    <b>Total Box:</b>
                    <input class="input-value" id="totalBoxes" disabled style="width: 105px; margin-left: 8px;"><br><br>
                    <b>Box Ranges:</b>
                    <input class="input-value" id="boxRange" disabled style="width: 200px; margin-top: 10px;">
                </div>
            </div>
            <div id="block4" <%--style="visibility: hidden"--%>>
                <br>
                <b style="color: #20509E; font: bold 22px Arial, Helvetica, sans-serif; margin-left: 35%;">Block 4</b>
                <div id="block-data" style=" margin-top: 30px; padding-left: 15px;
                color: #20509E; font: bold 17px Arial, Helvetica, sans-serif;">
                    <b>Item:</b>
                    <input class="input-value" id="itemName4" disabled style="width: 148px; margin-left: 12px;"><br><br>
                    <b>Total Carton:</b>
                    <input class="input-value" id="totalCarton" disabled style="width: 90px; margin-left: 4px;"><br><br>
                    <b>Carton Ranges:</b>
                    <input class="input-value" id="cartonRange" disabled style="width: 200px; margin-top: 3px"><br><br>
                    <b>Exporter Name:</b>
                    <input class="input-value" id="exporterName" disabled style="width: 200px; margin-top: 5px;">
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script src="http://code.jquery.com/jquery-latest.min.js "></script>
<script>

    let modal = document.getElementById("popup-msg");
    let conform_btn = document.getElementById("confirm-btn");

    conform_btn.onclick = function(){
        document.getElementById("popup-text").style.color = "rgba(92, 93, 94, 0.78)";
        modal.style.display = "none";
    };

    modal.style.display = "block";
    document.getElementById("popup-text").innerText = "Setting record...";
    document.getElementById("confirm-btn").style.visibility="hidden";

    setTimeout(function () {
        var json = localStorage.getItem("jsonValue");
        localStorage.clear();
        console.log("json...",json);
        var jsonParse = jQuery.parseJSON(json);
        let size = jsonParse.blockPresent;
        if(Number(size)===1){
            console.log("i1");
            /*document.getElementById("block1").style.visibility = "visible";*/
            document.getElementById("productID").value = jsonParse.productID;
            document.getElementById("date").value = jsonParse.creationDate;
            document.getElementById("time").value = jsonParse.creationTime;
            document.getElementById("itemName1").value = jsonParse.productName;
            document.getElementById("qunt1").value = jsonParse.quantity;
            document.getElementById("price").value = jsonParse.price;
            document.getElementById("supplierName").value = jsonParse.supplierName;

        }else if(Number(size)===2){
            console.log("2");
            /*document.getElementById("block1").style.visibility = "visible";
            document.getElementById("block2").style.visibility = "visible";*/
            document.getElementById("productID").value = jsonParse.productID;
            document.getElementById("date").value = jsonParse.creationDate;
            document.getElementById("time").value = jsonParse.creationTime;
            document.getElementById("itemName1").value = jsonParse.productName;
            document.getElementById("qunt1").value = jsonParse.quantity;
            document.getElementById("itemName2").value = jsonParse.productName;
            document.getElementById("qunt2").value = jsonParse.quantity;
            document.getElementById("price").value = jsonParse.price;
            document.getElementById("supplierName").value = jsonParse.supplierName;
            document.getElementById("machineNO").value = jsonParse.machineNumber;
            document.getElementById("temp").value = jsonParse.temperature;

        }else if(Number(size)===3){
            console.log("3");
            /*document.getElementById("block1").style.visibility = "visible";
            document.getElementById("block2").style.visibility = "visible";
            document.getElementById("block3").style.visibility = "visible";*/
            document.getElementById("productID").value = jsonParse.productID;
            document.getElementById("date").value = jsonParse.creationDate;
            document.getElementById("time").value = jsonParse.creationTime;
            document.getElementById("itemName1").value = jsonParse.productName;
            document.getElementById("qunt1").value = jsonParse.quantity;
            document.getElementById("itemName2").value = jsonParse.productName;
            document.getElementById("qunt2").value = jsonParse.quantity;
            document.getElementById("itemName3").value = jsonParse.productName;
            document.getElementById("qunt3").value = jsonParse.quantity;
            document.getElementById("price").value = jsonParse.price;
            document.getElementById("supplierName").value = jsonParse.supplierName;
            document.getElementById("machineNO").value = jsonParse.machineNumber;
            document.getElementById("temp").value = jsonParse.temperature;
            document.getElementById("totalBoxes").value = jsonParse.totalBoxes;
            document.getElementById("boxRange").value = jsonParse.boxNumberRange;

        }else if(Number(size)===4){
            console.log("4");
            /*document.getElementById("block1").style.visibility = "visible";
            document.getElementById("block2").style.visibility = "visible";
            document.getElementById("block3").style.visibility = "visible";
            document.getElementById("block4").style.visibility = "visible";*/
            document.getElementById("productID").value = jsonParse.productID;
            document.getElementById("date").value = jsonParse.creationDate;
            document.getElementById("time").value = jsonParse.creationTime;
            document.getElementById("itemName1").value = jsonParse.productName;
            document.getElementById("qunt1").value = jsonParse.quantity;
            document.getElementById("itemName2").value = jsonParse.productName;
            document.getElementById("qunt2").value = jsonParse.quantity;
            document.getElementById("itemName3").value = jsonParse.productName;
            document.getElementById("qunt3").value = jsonParse.quantity;
            document.getElementById("itemName4").value = jsonParse.productName;
            document.getElementById("price").value = jsonParse.price;
            document.getElementById("supplierName").value = jsonParse.supplierName;
            document.getElementById("machineNO").value = jsonParse.machineNumber;
            document.getElementById("temp").value = jsonParse.temperature;
            document.getElementById("totalBoxes").value = jsonParse.totalBoxes;
            document.getElementById("boxRange").value = jsonParse.boxNumberRange;
            document.getElementById("totalCarton").value = jsonParse.totalCarton;
            document.getElementById("cartonRange").value = jsonParse.cartonNumberRange;
            document.getElementById("exporterName").value = jsonParse.exporterName;
        }
        modal.style.display = "none";
    },2000);

</script>
</html>
