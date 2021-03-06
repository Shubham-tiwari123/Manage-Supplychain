package com.project.client.requestAPI;

import com.project.client.responseAPI.ConnectToServerResAPI;
import com.project.client.services.ConnectToServer;
import com.project.client.utils.ConstantClass;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "ConnectToServerReqAPI")
public class ConnectToServerReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ConnectToServer connect = new ConnectToServer();
        int count = 0;
        String resString = "";
        int statusCode = ConstantClass.SUCCESSFUL;
        try {
            System.out.println("client hit");
            URL url = new URL(ConstantClass.CONNECT_SERVER_URL1);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("hit server");
                if (connect.openSocket(ConstantClass.SOCKET_ADDRESS, ConstantClass.SOCKET_PORT_NUMBER)) {
                    System.out.println("socket");
                    if (connect.sendData(connect.verifyNetwork())) {
                        while (ConnectToServer.socket.isConnected()) {
                            System.out.println("waiting for response");
                            resString = connect.readServerData();
                            switch (count) {
                                case 0:
                                    if (resString.equals("200")) {
                                        System.out.println("Network is secured");
                                        String clientKeys = connect.prepareKeysToSend();
                                        if (connect.sendData(clientKeys)) {
                                            System.out.println("Client keys send...");
                                            count++;
                                        } else {
                                            statusCode = ConstantClass.FAILED;
                                            connect.closeConnections();
                                        }
                                    } else {
                                        System.out.println("Issue in network....try again");
                                        statusCode = ConstantClass.FAILED;
                                        connect.closeConnections();
                                    }
                                    break;

                                case 1:
                                    if (resString.equals("200")) {
                                        String serverKeys = connect.readServerData();
                                        System.out.println("server keys:\n" + serverKeys);
                                        if (connect.verifyServerKeys(serverKeys)) {
                                            System.out.println("keys verified");
                                            //store its and server keys in db
                                            count++;
                                            if(connect.storeServerKeys(serverKeys)) {
                                                connect.sendData("200");
                                                String data = connect.readServerData();
                                                if(data.equals("200OK"))
                                                    connect.closeConnections();
                                                else{
                                                    if(connect.deleteServerKeys())
                                                        System.out.println("server keys deleted");
                                                    System.out.println("Issue in server storing");
                                                    statusCode = ConstantClass.FAILED;
                                                    connect.closeConnections();
                                                }
                                            }else{
                                                System.out.println("keys not stored...db error");
                                                statusCode = ConstantClass.FAILED;
                                                connect.sendData("400");
                                                connect.closeConnections();
                                            }
                                        } else {
                                            System.out.println("keys not verified");
                                            statusCode = ConstantClass.FAILED;
                                            connect.sendData("400");
                                            connect.closeConnections();
                                        }
                                    }else {
                                        System.out.println("Tampered client keys ....try again");
                                        statusCode = ConstantClass.FAILED;
                                        connect.closeConnections();
                                    }
                                    break;
                            }
                            if (ConnectToServer.socket.isClosed()) {
                                System.out.println("socket closed "+ConnectToServer.socket.isConnected());
                                break;
                            }
                        }
                    } else {
                        statusCode = ConstantClass.FAILED;
                        System.out.println("Something went wrong....try again");
                        connect.closeConnections();
                    }
                } else {
                    statusCode = ConstantClass.FAILED;
                    System.out.println("Something went wrong....try again");
                }
                connect.closeConnections();
                ConnectToServerResAPI resAPI = new ConnectToServerResAPI();
                resAPI.readResponse(response,request,statusCode);
            }
            else{
                connect.closeConnections();
                System.out.println("socket is closed try again");
                ConnectToServerResAPI resAPI = new ConnectToServerResAPI();
                resAPI.readResponse(response,request,ConstantClass.FAILED);
            }
        }catch (Exception e){
            //System.out.println(e);
            try {
                connect.closeConnections();
                System.out.println("catch throwing....closing connection");
                ConnectToServerResAPI resAPI = new ConnectToServerResAPI();
                resAPI.readResponse(response,request,ConstantClass.BAD_REQUEST);
            } catch (Exception ex) {
                ex.printStackTrace();
                ConnectToServerResAPI resAPI = new ConnectToServerResAPI();
                resAPI.readResponse(response,request,ConstantClass.BAD_REQUEST);
            }
        }
    }
}
