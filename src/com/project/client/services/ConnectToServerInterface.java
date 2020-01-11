package com.project.client.services;

import com.project.client.entity.ClientKeys;

public interface ConnectToServerInterface {

    boolean openSocket(String address, int port) throws Exception;

    String readServerData() throws Exception;

    boolean sendData(String data) throws Exception;

    void closeConnections() throws Exception;

    String verifyNetwork()  throws Exception;

    String prepareKeys() throws Exception;

    boolean verifyServerKeys(String clientKeys) throws Exception;

    boolean storeServerKeys(String keys) throws Exception;

    String calculateHash(String data) throws Exception;

    ClientKeys generateKeys() throws Exception;
}
