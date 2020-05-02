package com.project.server1.requestAPI.website;

import com.project.server1.responseAPI.website.ConnectDeviceResAPI;
import com.project.server1.services.ConnectToDevice;
import com.project.server1.utils.ConstantClass;

import javax.servlet.http.HttpServletResponse;

public class SocketThread extends Thread {
    private HttpServletResponse response;
    private final ConnectToDevice connect = new ConnectToDevice();

    public SocketThread(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void run(){
        try {
            int count = 0;
            int statusCode = 200;
            String clientData = "";
            String clientKeys="";
            System.out.println("run function");
            if(connect.connectUsingSocket(4000)){
                System.out.println("connected");
                Thread.sleep(2000);
                while (ConnectToDevice.socket.isConnected()){
                    clientData = connect.readClientData();
                    System.out.println("Data from client:" + clientData);
                    switch (count) {
                        case 0:
                            if (connect.verifyNetwork(clientData)) {
                                System.out.println("Network is secured");
                                if (connect.sendData("200"))
                                    count++;
                                else {
                                    statusCode = ConstantClass.FAILED;
                                    connect.closeConnections();
                                }
                            } else {
                                statusCode = ConstantClass.FAILED;
                                connect.sendData("400");
                                connect.closeConnections();
                                System.out.println("Error in network.....Closing socket");
                            }
                            break;

                        case 1:
                            if (connect.verifyClientKeys(clientData)) {
                                clientKeys = clientData;
                                System.out.println("keys verified");
                                String serverKeys = connect.getServerKeys();
                                if (connect.sendData("200")) {
                                    System.out.println("Sending server keys to client");
                                    if (connect.sendData(serverKeys)) {
                                        count++;
                                    } else {
                                        statusCode = ConstantClass.FAILED;
                                        connect.closeConnections();
                                    }
                                } else {
                                    statusCode = ConstantClass.FAILED;
                                    connect.closeConnections();
                                }
                            } else {
                                statusCode = ConstantClass.FAILED;
                                connect.sendData("400");
                                connect.closeConnections();
                                System.out.println("Client keys are tampered...Closing socket");
                            }
                            break;

                        case 2:
                            if (clientData.equals("200")) {
                                //store the data in db and will send a final 200 to client
                                if(connect.storeClientKeys(clientKeys)) {
                                    System.out.println("keys stored");
                                    connect.sendData("200OK");
                                    connect.closeConnections();
                                }
                                else{
                                    System.out.println("Error in db");
                                    connect.sendData("400");
                                    statusCode = ConstantClass.FAILED;
                                    connect.closeConnections();
                                }
                            }else{
                                System.out.println("Server keys are tampered");
                                statusCode = ConstantClass.FAILED;
                                connect.closeConnections();
                            }
                    }
                    if (ConnectToDevice.socket.isClosed()) {
                        System.out.println("socket is closed");
                        ConnectToDevice.server.close();
                        System.out.println(ConnectToDevice.server.getLocalPort()+" "+
                                ConnectToDevice.server.isClosed());
                        break;
                    }
                }
            } else {
                statusCode = ConstantClass.FAILED;
                System.out.println("Something went wrong....try again");
            }

            if (statusCode == ConstantClass.SUCCESSFUL) {
                System.out.println("Keys exchanged and stored in db");
                ConnectDeviceResAPI resAPI = new ConnectDeviceResAPI();
                resAPI.sendResponse(response,ConstantClass.SUCCESSFUL);
            }else{
                ConnectDeviceResAPI resAPI = new ConnectDeviceResAPI();
                resAPI.sendResponse(response,ConstantClass.FAILED);
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("catch socket...closing con");
            try {
                connect.closeConnections();
                ConnectDeviceResAPI resAPI = new ConnectDeviceResAPI();
                resAPI.sendResponse(response,ConstantClass.FAILED);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
