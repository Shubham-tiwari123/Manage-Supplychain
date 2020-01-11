package com.project.client.services;

import com.project.client.entity.ClientKeys;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.ws.rs.client.Client;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Random;

public class ConnectToServer implements ConnectToServerInterface {

    private static Socket socket;
    private static DataOutputStream out;
    private CommonFunction commonFunction = new CommonFunction();

    @Override
    public boolean openSocket(String address, int port) throws Exception {
        socket = new Socket(address, port);
        System.out.println("connected");
        out = new DataOutputStream(socket.getOutputStream());
        return true;
    }

    @Override
    public String readServerData() throws Exception {
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
    public String verifyNetwork() throws Exception {
        Random random = new Random();
        int data = 1000 + random.nextInt(5000);
        String hashData = calculateHash(String.valueOf(data));
        JSONObject object = new JSONObject();
        object.put("data", data);
        object.put("hash", hashData);
        return object.toString();
    }

    @Override
    public String prepareKeys() throws Exception {
        ClientKeys keys = commonFunction.getKeysFromDB("");   //specify collection name
        String pubModHash = calculateHash(keys.getPublicKeyModules().toString());
        String pubExpoHash = calculateHash(keys.getPublicKeyExpo().toString());
        JSONObject object = new JSONObject();
        object.put("modValue", keys.getPublicKeyModules().toString());
        object.put("modHash", pubModHash);
        object.put("expoValue", keys.getPublicKeyExpo().toString());
        object.put("expoHash", pubExpoHash);
        return object.toString();
    }

    @Override
    public boolean verifyServerKeys(String clientKeys) throws Exception {
        System.out.println("Verifying keys");
        Object object = new JSONParser().parse(clientKeys);
        JSONObject jsonObject = (JSONObject) object;
        String modHash = (String) jsonObject.get("modHashC");
        String expoHash = (String) jsonObject.get("expoHashC");
        String modValue = (String) jsonObject.get("modValueC");
        String expoValue = (String) jsonObject.get("expoValueC");

        return modHash.equals(calculateHash(modValue)) && expoHash.equals(calculateHash(expoValue));
    }

    @Override
    public boolean storeServerKeys(String keys) throws Exception {
        return false;
    }

    @Override
    public String calculateHash(String data) throws Exception {
        return commonFunction.calculateHash(data);
    }

    @Override
    public ClientKeys generateKeys() throws Exception {
        System.out.println("generating keys");
        ClientKeys clientKeys = new ClientKeys();
        KeyPairGenerator keyPair = KeyPairGenerator.getInstance("RSA");
        keyPair.initialize(3072);//3072
        KeyPair pair = keyPair.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        // Generating key-pair
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec rsaPublicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        RSAPrivateKeySpec rsaPrivateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
        //Setting public keys
        clientKeys.setPublicKeyExpo(rsaPublicKeySpec.getPublicExponent());
        clientKeys.setPublicKeyModules(rsaPublicKeySpec.getModulus());
        //Setting private keys
        clientKeys.setPrivateKeyExpo(rsaPrivateKeySpec.getPrivateExponent());
        clientKeys.setPrivateKeyModules(rsaPrivateKeySpec.getModulus());
        return clientKeys;
    }
}
