<%@ page import="javax.swing.*" %>
<html>
<head>
    <title>
        Prepare Block
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
        <button id="getBlockID" onclick="getBlockID()">GET ID</button>
        <div id="prepare-block">
            <div id="caption">
                <p style="margin-top: 48px;">Block ID</p>
                <p style="margin-top: 48px">Product Name</p>
                <p style="margin-top: 48px">Quantity</p>
                <p style="margin-top: 48px">Suplier Name</p>
                <p style="margin-top: 48px">Price</p>
            </div>
            <div id="input-box">
                <input id="setBlockID" disabled required><br>
                <select class="minimal" id="product-name">
                    <option value="1">Select product</option>
                    <option value="">Product Name</option>
                    <option value="">Product Name</option>
                    <option value="">Product Name</option>
                    <option value="">Product Name</option>
                </select><br>
                <input id="qunt" type="number" max="100" min="10" required><br>
                <select class="minimal" id="supplier-name">
                    <option value="1">Select supplier</option>
                    <option>Supplier Name</option>
                    <option>Supplier Name</option>
                    <option>Supplier Name</option>
                    <option>Supplier Name</option>
                </select><br>
                <input type="number" id="price" required><br>
            </div>
                <button type="submit" onclick="sendBlockData()" id="send-btn">SEND</button>
        </div>
    </div>
</div>
</body>
<script src="http://code.jquery.com/jquery-latest.min.js "></script>
<script>

    var modal = document.getElementById("popup-msg");
    var btn = document.getElementById("confirm-btn");
    document.getElementById("setBlockID").value = "";
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
            var response = $.get('/get-id');
            response.success(function (result) {
                modal.style.display = "none";
                document.getElementById("setBlockID").value = result;
                document.getElementById("getBlockID").disabled = true;
                document.getElementById("getBlockID").style.backgroundColor = "grey";
            });
            response.error(function (jqXHR, textStatus, errorThrown) {
                modal.style.display = "none";
                alert("Server error...pls wait")
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
        console.log("daate:",today);

        var blockId = document.getElementById("setBlockID").value;
        var productName = document.getElementById("product-name").value;
        var qunt = document.getElementById("qunt").value;
        var supplier = document.getElementById("supplier-name").value;
        var price = document.getElementById("price").value;

        console.log("block",blockId,productName,qunt,supplier,price);
        /*if((blockId.length===0) || (qunt.length===0) || (price.length===0)){
            console.log("if");
            alert("Fill the form")
        }
        else if((productName==='Select product')||(supplier==='Select supplier')){
            console.log("if");
            alert("Product or supplier name is not valid")
        }
        else{
            modal.style.display = "block";
            console.log("else");
            var response = $.post('/send-block',{
                productQun:qunt,
                productName:productName,
                blockID:blockId,
                supplierName:supplier,
                price:price
            });

            response.success(function (result) {
                if(result==200) {
                    modal.style.display = "none";
                    alert("Data send");
                    document.getElementById("setBlockID").value = " ";
                    document.getElementById("product-name").value = "Select product";
                    document.getElementById("qunt").value = "";
                    document.getElementById("qunt").style.borderColor = "none";
                    document.getElementById("supplier-name").value = "Select supplier";
                    document.getElementById("price").value = 0;
                    document.getElementById("getBlockID").disabled = false;
                    document.getElementById("getBlockID").style.backgroundColor = "#57B846";
                }else{
                    modal.style.display = "none";
                    alert("Some error occurred...pls try again")
                }
            });

            response.error(function (jqXHR, textStatus, errorThrown) {
                alert("Server error...pls wait");
            })
        }
*/

        let flag = await validateForm(blockId,productName,qunt, supplier,price);
        if(flag){
            modal.style.display = "block";
            document.getElementById("popup-text").innerText = "Submitting record...";
            document.getElementById("confirm-btn").style.visibility="hidden";
            
            console.log("valid")
            var response = $.post('/send-block',{
                productQun:qunt,
                productName:productName,
                blockID:blockId,
                supplierName:supplier,
                price:price,
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
                            window.location.replace("/prepare_block")
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
            console.log("not-valid")
        }
    }

    function validateForm(blockID, productName, qunt, supplier, price) {
        console.log("validating form");
        let letters = /^[A-Za-z]+$/;
        if(blockID===""){
            alert("Please get the blokID");
            document.getElementById("setBlockID").focus();
            return false;
        }
        if(productName==="1"){
            alert("Please provide product name");
            document.getElementById("product-name").focus();
            return false;
        }
        if(supplier==="1"){
            alert("Please provide supplier name");
            document.getElementById("supplier-name").focus();
            return false;
        }
        if(qunt===""||isNaN(qunt)){
            alert("Please provide valid quantity");
            document.getElementById("qunt").focus();
            return false;
        }
        if(price===""||isNaN(price)){
            alert("Please provide valid price");
            document.getElementById("price").focus();
            return false;
        }
        return true;
    }

</script>
</html>
