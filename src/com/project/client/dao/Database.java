package com.project.client.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.result.DeleteResult;
import com.project.client.entity.ClientKeys;
import com.project.client.entity.ServerKeys;
import com.project.client.utils.VariableClass;
import org.bson.Document;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Database implements DatabaseInterface {

    private static MongoDatabase database;

    @Override
    public boolean createDbConn(){
        MongoClient client = new MongoClient(VariableClass.IP_ADDRESS, VariableClass.PORT_NUMBER);
        database = client.getDatabase(VariableClass.DATABASE_NAME);
        return true;
    }

    @Override
    public boolean checkCollection(String collectionName) {
        System.out.println("Checking if collection exists or not.....");
        MongoIterable<String> iterable = database.listCollectionNames();
        Set<String> colName = new TreeSet<String>();
        for (String col : iterable) {
            colName.add(col);
        }
        if (!colName.contains(collectionName)) {
            System.out.println("collection does not exists:");
            System.out.println("Creating new collection");
            database.createCollection(collectionName);
            return true;
        }
        System.out.println("exists");
        return true;
    }

    @Override
    public ServerKeys getServerKeys(String collectionName) throws Exception {
        ServerKeys keys = new ServerKeys();
        if (createDbConn()) {
            if (checkCollection(collectionName)) {
                MongoCollection collection = database.getCollection(collectionName);
                System.out.println("getting keys from db");
                List<Document> list = (List<Document>) collection.find(new Document("serverKeys",
                        "Server Keys")).into(new ArrayList<Document>());
                System.out.println("list:" + list.size());

                if (!list.isEmpty()) {
                    for (Document val : list) {
                        System.out.println("getting server keys if");
                        String publicKeyModules = val.getString("serverMod");
                        String publicKeyExpo = val.getString("serverExpo");

                        System.out.println("setting server keys");
                        keys.setPublicKeyExpo(new BigInteger(publicKeyExpo));
                        keys.setPublicKeyModules(new BigInteger(publicKeyModules));
                    }
                    return keys;
                } else {
                    return null;
                }

            }
        }
        return null;
    }

    @Override
    public boolean storeServerKeys(ServerKeys keys, String collectionName) throws Exception {
        if (createDbConn()) {
            if (checkCollection(collectionName)) {
                System.out.println("saving client and server keys in db");
                Document document = new Document("serverKeys", "Server Keys")
                        .append("serverPubMod", keys.getPublicKeyModules().toString())
                        .append("serverPubExpo", keys.getPublicKeyExpo().toString());
                database.getCollection(collectionName).insertOne(document);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean storeClientKeys(ClientKeys keys, String collectionName) throws Exception {
        if (createDbConn()) {
            if (checkCollection(collectionName)) {
                System.out.println("saving client and server keys in db");
                Document document = new Document("clientKeys","Client Keys")
                        .append("clientPubMod", keys.getPublicKeyModules().toString())
                        .append("clientPubExpo", keys.getPublicKeyExpo().toString())
                        .append("clientPriExpo", keys.getPrivateKeyExpo().toString())
                        .append("clientPriMod", keys.getPrivateKeyModules().toString());
                database.getCollection(collectionName).insertOne(document);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public ClientKeys getClientKeys(String collectionName) throws Exception {
        ClientKeys keys = new ClientKeys();
        if (createDbConn()) {
            if (checkCollection(collectionName)) {
                MongoCollection collection = database.getCollection(collectionName);
                System.out.println("getting keys from db");
                List<Document> list = (List<Document>) collection.find(new Document("clientKeys",
                        "Client Keys")).into(new ArrayList<Document>());
                System.out.println("list:" + list.size());

                if (!list.isEmpty()) {
                    for (Document val : list) {
                        System.out.println("getting client keys if");
                        String publicKeyModules = val.getString("clientPubMod");
                        String publicKeyExpo = val.getString("clientPubExpo");

                        System.out.println("setting server keys");
                        keys.setPublicKeyExpo(new BigInteger(publicKeyExpo));
                        keys.setPublicKeyModules(new BigInteger(publicKeyModules));
                    }
                    return keys;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean deleteServerKeys(String collectionName) throws Exception {
        if (createDbConn()) {
            if (checkCollection(collectionName)) {
                System.out.println("deleting database");
                MongoCollection collection = database.getCollection(collectionName);
                DeleteResult result = collection.deleteOne(new Document("serverKeys", "Server Keys"));
                return result.getDeletedCount() != 0;
            }
        }
        return false;
    }
}