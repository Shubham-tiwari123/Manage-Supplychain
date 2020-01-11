package com.project.client.services;
import com.project.client.entity.ClientKeys;
import java.util.ArrayList;

public interface TransferBlockInterface {

    String prepareBlock(long quantity,String itemName) throws Exception;

    String calBlockHash(String data) throws Exception;

    ArrayList<byte[]> encryptBlock(ClientKeys keys,String encryptString) throws Exception;

    ClientKeys getKeysFromDatabase() throws Exception;

    long generateBlockID();
}
