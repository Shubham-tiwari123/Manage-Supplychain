<html>
<head>
    <title>Database</title>
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

        #topic-name {
            margin-top: 60px;
            width: 98%;
            height: 12%;
        }

        #database-view {
            width: 98%;
            height: 75%;
            overflow-y: auto;
        }

        #display-data {
            width: 95%;
            height: 60px;
            margin-top: 20px;
            background-color: white;
            margin-left: 35px;
            border-radius: 40px;
            border: 1px solid rgba(122, 122, 122, 0.63);;
            box-shadow: 5px 10px 5px rgba(122, 122, 122, 0.63);
        }

        #input-box-data {
            width: 150px;
            height: 40px;
            border: none;
            text-align: center;
            color: #20509E;
            float: left;
            font: bold 18px Arial, Helvetica, sans-serif;
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

    </style>
</head>
<body>

<div id="popup-msg" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <p id="popup-text">Deactivate Patients</p>
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
        <div id="topic-name">
            <br><br>
            <b style="color: #20509E; font: bold 20px Arial, Helvetica, sans-serif; margin-left: 80px">
                Product id</b>
            <b style="color: #20509E; font: bold 20px Arial, Helvetica, sans-serif; margin-left: 100px">
                Time</b>
            <b style="color: #20509E; font: bold 20px Arial, Helvetica, sans-serif;margin-left: 125px">
                Date</b>
            <b style="color: #20509E; font: bold 20px Arial, Helvetica, sans-serif;margin-left: 130px">
                Blocks</b>
        </div>
        <div id="database-view">
            <%--<div id="display-data">
                <div id="input-box-data" style="margin-left: 30px;">
                    <p>Product ID</p>
                </div>
                <div id="input-box-data" style="margin-left: 30px;">
                    <p>Time</p>
                </div>
                <div id="input-box-data" style="margin-left: 30px;">
                    <p>Date</p>
                </div>
                <div id="input-box-data" style="margin-left: 30px;">
                    <p>4</p>
                </div>
                <div id="input-box-data" style="margin-left: 30px; margin-top: 15px">
                    <button style="width: 80px; height: 30px; background-color: #57B846; border: none;
                        box-shadow: 5px 5px 10px rgba(65, 65, 65, 0.69); border-radius: 20px;
                        font: bold 16px Arial, Helvetica, sans-serif; color: white; margin-left: 40px">View
                    </button>
                </div>
            </div>--%>
        </div>
    </div>
</div>
</body>
<script src="http://code.jquery.com/jquery-latest.min.js "></script>
<script>

    let modal = document.getElementById("popup-msg");
    let record;
    modal.style.display =  "block";
    document.getElementById("popup-text").innerText = "Getting Records..";

    let response = $.post('/product_info',{
        productID:-1
    });

    function detailView(jsonValue){
        localStorage.setItem("jsonValue",record[jsonValue]);
        window.location.replace("/detail_view")
    }

    setTimeout(function () {
        response.success(function (result) {
            const resultObj = jQuery.parseJSON(result);
            if(resultObj.statusCode===200){
                record = resultObj.result;
                // set the values
                var html="";
                for(var i=0;i<record.length;i++){
                    var json = jQuery.parseJSON(record[i]);
                    console.log("json",json);
                    html +=("<div id=\"display-data\">\n" +
                        "                <div id=\"input-box-data\" style=\"margin-left: 30px;\">\n" +
                        "                    <p>"+json.productID+"</p>\n" +
                        "                </div>\n" +
                        "                <div id=\"input-box-data\" style=\"margin-left: 30px;\">\n" +
                        "                    <p>"+json.creationTime+"</p>\n" +
                        "                </div>\n" +
                        "                <div id=\"input-box-data\" style=\"margin-left: 30px;\">\n" +
                        "                    <p>"+json.creationDate+"</p>\n" +
                        "                </div>\n" +
                        "                <div id=\"input-box-data\" style=\"margin-left: 30px;\">\n" +
                        "                    <p>"+json.blockPresent+"</p>\n" +
                        "                </div>\n" +
                        "                <div id=\"input-box-data\" style=\"margin-left: 30px; margin-top: 15px\">\n" +
                        "                    <button onclick=\"detailView(\'"+i+"'\)\" style=\"width: 80px; height: 30px; background-color: #57B846; border: none;\n" +
                        "                        box-shadow: 5px 5px 10px rgba(65, 65, 65, 0.69); border-radius: 20px;\n" +
                        "                        font: bold 16px Arial, Helvetica, sans-serif; color: white; margin-left: 40px\">View\n" +
                        "                    </button>\n" +
                        "                </div>\n" +
                        "            </div>")
                }
                $("#database-view").append(html);
                setTimeout(function () {
                    modal.style.display =  "none";
                },1000)
            }else{
                document.getElementById("popup-text").innerText = "Server error...";
                document.getElementById("popup-text").style.color = "#BA0606";
                document.getElementById("confirm-btn").style.visibility ="visible";
            }
        });

        response.error(function (jqXHR, textStatus, errorThrown) {
            document.getElementById("popup-text").innerText = "Server error...";
            document.getElementById("popup-text").style.color = "#BA0606";
            document.getElementById("confirm-btn").style.visibility ="visible";
        })
    }, 2000);
</script>
</html>
