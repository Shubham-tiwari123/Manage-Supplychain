package com.project.client.dao;

import com.project.client.entity.ClientKeys;
import com.project.client.entity.ServerKeys;

public interface DatabaseInterface {

    boolean createDbConn();

    boolean checkCollection(String collectionName);

    ServerKeys getServerKeys(String collectionName) throws Exception;

    boolean storeServerKeys(ServerKeys keys, String collectionName) throws Exception;

    boolean storeClientKeys(ClientKeys keys, String collectionName) throws Exception;

    ClientKeys getClientKeys(String collectionName) throws Exception;

    boolean deleteServerKeys(String collectionName) throws Exception;

    boolean deleteClientKeys(String collectionName) throws Exception;

    boolean checkKeysExists(String collectionName) throws Exception;
}