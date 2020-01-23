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
            color: #20509E;"><a href="/prepare-block" method="get">Prepare Block</a></p>
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
        <div id="pc-img">
            <img src="static/images/server.png" style="width: 280px; height: 250px">
            <div id="server-cap">SERVER</div>
        </div>
        <div id="client-img">
            <img src="static/images/client.png" style="width: 220px; height: 180px">
            <div id="client-cap">CLIENT</div>
        </div>
        <button id="connectBtn">CONNECT</button>
        <div id="table-style">
            <table id="public-keys">
                <tr>
                    <th style="border-radius: 16px 0px 0px 0px; width: 15%">Device</th>
                    <th style="border-radius: 0px 16px 0px 0px;width: 85%">Public Keys</th>
                </tr>
                <tr>
                    <td>PC</td>
                    <td>dhbscudcbebca3yebdiaosxmudhceuhasoxkjceubcwimxjcefecbnsnsdhceucbenscuccdycd</td>
                </tr>
                <tr>
                    <td style="border-radius: 0px 0px 16px 16px">Server</td>
                    <td style="border-radius: 0px 0px 16px 16px">
                        dhbscudcbebca3yebdiaosxmudhceuhasoxkjceubcwimxjcefecbnsnsdhceucbenscuccdycd
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
