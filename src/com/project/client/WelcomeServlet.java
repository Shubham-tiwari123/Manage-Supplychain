package com.project.client;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;
@WebServlet(name = "WelcomeServlet",urlPatterns = {"/welcome"})
public class WelcomeServlet extends HttpServlet {

    private static ExtraFunctions extraFunctions = new ExtraFunctions();
    private static Socket socket;
    private static DataOutputStream out;

    private static String hashValue(String data) throws Exception {
        return extraFunctions.calculateHash(data);
    }

    private static boolean openSocket(String address, int port) throws Exception {
        socket = new Socket(address, port);
        System.out.println("connected");
        out = new DataOutputStream(socket.getOutputStream());
        return true;
    }

    private static boolean sendData(String data) throws Exception {
        if (socket.isConnected()) {
            out.writeUTF(data);
            return true;
        }
        return false;
    }

    private static String readServerResponse() throws Exception {
        DataInputStream inputStream = new DataInputStream(new BufferedInputStream
                (socket.getInputStream()));
        return inputStream.readUTF();
    }

    private static void closeAllConnection() throws Exception {
        out.close();
        socket.close();
    }

    private static String verifyNetwork() throws Exception {
        Random random = new Random();
        int data = 1000 + random.nextInt(5000);
        String hashData = hashValue(String.valueOf(data));
        JSONObject object = new JSONObject();
        object.put("data", data);
        object.put("hash", hashData);
        return object.toString();


    }

    private static String prepareKeys() throws Exception {
        SetKeys keys = new SetKeys();
        String pubModHash = hashValue(keys.getPublicKeyModules().toString());
        String pubExpoHash = hashValue(keys.getPublicKeyExpo().toString());
        JSONObject object = new JSONObject();
        object.put("modValue", keys.getPublicKeyModules().toString());
        object.put("modHash", pubModHash);
        object.put("expoValue", keys.getPublicKeyExpo().toString());
        object.put("expoHash", pubExpoHash);
        return object.toString();
    }

    private static boolean verifyServerKeys(String serverKeys) throws Exception {
        System.out.println("Verifying keys");
        Object object = new JSONParser().parse(serverKeys);
        JSONObject jsonObject = (JSONObject) object;
        String modHash = (String) jsonObject.get("modHashS");
        String expoHash = (String) jsonObject.get("expoHashS");
        String modValue = (String) jsonObject.get("modValueS");
        String expoValue = (String) jsonObject.get("expoValueS");

        return modHash.equals(extraFunctions.calculateHash(modValue)) && expoHash
                .equals(extraFunctions.calculateHash(expoValue));
    }

    private static boolean storeDb() throws Exception {
        return true;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        int count = 0;
        String resString = "";
        int statusCode = 200;
        try {
            System.out.println("client hit");
            URL url = new URL("http://localhost:8080/connect-device");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            System.out.println("jhfbfgoj");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("ok");
                if (openSocket("127.0.0.1", 4000)) {
                    String data = verifyNetwork();
                    if (sendData(data)) {
                        while (socket.isConnected()) {
                            System.out.println("waiting for response");
                            resString = readServerResponse();
                            System.out.println("resp:" + resString);
                            switch (count) {
                                case 0:
                                    if (resString.equals("200")) {
                                        String clientKeys = prepareKeys();
                                        if (sendData(clientKeys)) {
                                            System.out.println("case 1 true");
                                            count++;
                                        } else {
                                            statusCode = 400;
                                            closeAllConnection();
                                        }
                                    } else {
                                        System.out.println("Issue in network....try again");
                                        statusCode = 400;
                                        closeAllConnection();
                                    }
                                    break;

                                case 1:
                                    if (resString.equals("200")) {
                                        String serverKeys = readServerResponse();
                                        System.out.println("server keys:\n" + serverKeys);
                                        if (verifyServerKeys(serverKeys)) {
                                            System.out.println("keys verified");
                                            //store its and server keys in db
                                            count++;
                                            if(storeDb()) {
                                                sendData("200");
                                                data = readServerResponse();
                                                if(data.equals("200OK"))
                                                    closeAllConnection();
                                                else{
                                                    //delete the keys
                                                    System.out.println("Issue in server storing");
                                                    statusCode = 400;
                                                    closeAllConnection();
                                                }
                                            }else{
                                                System.out.println("keys not stored...db error");
                                                statusCode = 400;
                                                sendData("400");
                                                closeAllConnection();
                                            }
                                        } else {
                                            System.out.println("keys not verified");
                                            statusCode = 400;
                                            sendData("400");
                                            closeAllConnection();
                                        }
                                    }else {
                                        System.out.println("Tampered client keys ....try again");
                                        statusCode = 400;
                                        closeAllConnection();
                                    }
                                    break;
                            }
                            if (socket.isClosed()) {
                                System.out.println("socket closedddddddddd");
                                break;
                            }
                        }
                    } else {
                        statusCode = 400;
                        System.out.println("Something went wrong....try again");
                        closeAllConnection();
                    }
                } else {
                    statusCode = 400;
                    System.out.println("Something went wrong....try again");
                }

                if (statusCode == 200) {
                    System.out.println("Keys exchanged and stored in db");
                }
            }
            else{
                System.out.println("socket is closed try again");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        writer.println("WELCOME TO CLIENT SIDE");
    }
}
