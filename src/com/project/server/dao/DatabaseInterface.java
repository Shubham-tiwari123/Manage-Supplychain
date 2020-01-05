package com.project.server.dao;

import com.project.server.entity.ClientKeys;
import com.project.server.entity.ServerKeys;

import java.util.ArrayList;

public interface DatabaseInterface {

    boolean verifySignature(String signature,long deviceID) throws Exception;

    boolean createDbConnection(String collectionName) throws Exception;

    boolean checkCollection(String collectionName) throws Exception;

    boolean storeSignature(String signature,long deviceID) throws Exception;

    boolean storeClientKeys(ClientKeys keys, String collectionName) throws Exception;

    boolean getClientKeys(String collectionName, String deviceSignature) throws Exception;

    boolean storeServerKeys(ServerKeys keys, String collectionName) throws Exception;

    ServerKeys getServerKeys(String collectionName) throws Exception;

    boolean saveGenesisBlock(String collectionName, ArrayList<byte[]> data, long productID)
            throws Exception;

    boolean updateChain(ArrayList<byte[]> data, long productId, String collectionName) throws Exception;

    boolean getServerPrivateKeys(String collectionName) throws Exception;

    ArrayList<ArrayList<byte[]>> getSpecificData(long productId, String collectionName) throws Exception;

}
