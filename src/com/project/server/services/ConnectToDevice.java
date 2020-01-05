package com.project.server.services;

import com.project.server.dao.Database;
import com.project.server.entity.ServerKeys;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectToDevice implements ConnectDeviceInterface {
    public static Socket socket = null;
    private static DataOutputStream out = null;
    private ExtraFunction extraFunction = new ExtraFunction();
    private Database database = new Database();
    public static ServerSocket server;

    @Override
    public boolean connectUsingSocket(int port) throws Exception {
        server = new ServerSocket(port);
        System.out.println("Socket started....");
        socket= server.accept();
        out = new DataOutputStream(socket.getOutputStream());
        System.out.println("Waiting for client");
        return true;
    }

    @Override
    public String readClientData() throws Exception {
        DataInputStream inputStream = new DataInputStream(new BufferedInputStream
                (socket.getInputStream()));
        return inputStream.readUTF();
    }

    @Override
    public boolean sendData(String data) throws Exception {
        if (socket.isConnected()) {
            out.writeUTF(data);
            return true;
        }
        return false;
    }

    @Override
    public void closeConnections() throws Exception {
        out.close();
        socket.close();
    }

    @Override
    public boolean verifyNetwork(String data) throws Exception {
        Object object = new JSONParser().parse(data);
        JSONObject jsonObject = (JSONObject) object;
        long num = (long) jsonObject.get("data");
        String hash = (String) jsonObject.get("hash");
        String calHash = calculateHash(String.valueOf(num));
        return calHash.equals(hash);
    }

    @Override
    public String getServerKeys() throws Exception {
        ServerKeys keys = database.getServerKeys("");
        String pubModHash = calculateHash(keys.getPublicKeyModules().toString());
        String pubExpoHash = calculateHash(keys.getPublicKeyExpo().toString());
        JSONObject object = new JSONObject();
        object.put("modValueS", keys.getPublicKeyModules().toString());
        object.put("modHashS", pubModHash);
        object.put("expoValueS", keys.getPublicKeyExpo().toString());
        object.put("expoHashS", pubExpoHash);
        return object.toString();
    }

    @Override
    public boolean verifyClientKeys(String clientKeys) throws Exception {
        System.out.println("Verifying client keys send by server");
        Object object = new JSONParser().parse(clientKeys);
        JSONObject jsonObject = (JSONObject) object;
        String modHash = (String) jsonObject.get("modHash");
        String expoHash = (String) jsonObject.get("expoHash");
        String modValue = (String) jsonObject.get("modValue");
        String expoValue = (String) jsonObject.get("expoValue");
        return modHash.equals(calculateHash(modValue)) && expoHash.equals(calculateHash(expoValue));
    }

    @Override
    public boolean storeClientKeys(String keys) throws Exception {
        return true;
    }

    @Override
    public String calculateHash(String data) throws Exception {
        return extraFunction.calculateHash(data);
    }

    @Override
    public ServerKeys generateKeys() throws Exception {
        return null;
    }
}
