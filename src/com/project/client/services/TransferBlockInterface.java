package com.project.client.services;
import com.project.client.entity.ClientKeys;
import com.project.client.entity.ServerKeys;

import java.util.ArrayList;

public interface TransferBlockInterface {

    String prepareBlock(long quantity,String itemName) throws Exception;

    String calBlockHash(String data) throws Exception;

    ArrayList<byte[]> encryptBlock(ServerKeys keys,String encryptString) throws Exception;

    ServerKeys getKeysFromDatabase() throws Exception;

    long generateBlockID();
}
