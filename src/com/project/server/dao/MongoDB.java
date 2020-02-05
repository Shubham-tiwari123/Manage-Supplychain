package com.project.server.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.project.server.entity.ClientKeys;
import com.project.server.entity.ServerKeys;
import com.project.server.utils.VariableClass;
import org.bson.Document;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MongoDB implements MongoDBInterface {

    private static MongoClient client;
    private static MongoDatabase database;
    private static MongoIterable<String> iterable;
    private static MongoCollection collection;
    private static Set<String> colName;

//    private ConnectToDevice connectToDevice = new ConnectToDevice();

    @Override
    public boolean verifySignature(String signature) throws Exception {
        return false;
    }

    @Override
    public boolean createDbConnection() throws Exception {
        client = new MongoClient(VariableClass.IP_ADDRESS, VariableClass.PORT_NUMBER);
        database = client.getDatabase(VariableClass.DATABASE_NAME);
        return true;
    }

    @Override
    public boolean checkCollection(String collectionName) throws Exception {
        System.out.println("Checking if collection exists or not.....");
        iterable = database.listCollectionNames();
        colName = new TreeSet<String>();
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
    public boolean storeSignature(String signature, long deviceID) throws Exception {
        return false;
    }

    @Override
    public boolean storeClientKeys(ClientKeys keys, String collectionName,String signature)
            throws Exception {

        if (createDbConnection()) {
            if (checkCollection(collectionName)) {
                System.out.println("Storing keys");
                Document document = new Document("userName", signature)
                        .append("publicKeyModules", keys.getClientPubKeyMod().toString())
                        .append("publicKeyExpo", keys.getClientPubKeyExpo().toString());
                database.getCollection(collectionName).insertOne(document);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public ClientKeys getClientKeys(String collectionName, String deviceSignature) throws Exception {
        ClientKeys keys = new ClientKeys();
        if (createDbConnection()) {
            if (checkCollection(collectionName)) {
                MongoCollection<Document> collection = database.getCollection(collectionName);
                System.out.println("getting client keys from db");
                List<Document> list = collection.find(new Document("userName", deviceSignature))
                        .into(new ArrayList<Document>());

                System.out.println("list:" + list.size());
                if (!list.isEmpty()) {
                    for (Document val : list) {
                        String publicKeyModules = val.getString("publicKeyModules");
                        String publicKeyExpo = val.getString("publicKeyExpo");

                        System.out.println("setting server keys");
                        keys.setClientPubKeyMod(new BigInteger(publicKeyModules));
                        keys.setClientPubKeyExpo(new BigInteger(publicKeyExpo));
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
    public boolean storeServerKeys(ServerKeys keys, String collectionName) throws Exception {
        if (createDbConnection()) {
            if (checkCollection(collectionName)) {
                collection = database.getCollection(collectionName);
                List<Document> user = (List<Document>) collection.find(new Document("keys", "serverKeys")).
                        into(new ArrayList<Document>());
                if (user.isEmpty()) {
                    Document document = new Document("keys", "serverKeys")
                            .append("publicKeyModules", keys.getPublicKeyModules().toString())
                            .append("publicKeyExpo", keys.getPublicKeyExpo().toString())
                            .append("privateKeyModules", keys.getPrivateKeyModules().toString())
                            .append("privateKeyExpo", keys.getPrivateKeyExpo().toString());
                    database.getCollection(collectionName).insertOne(document);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public ServerKeys getServerKeys(String collectionName) throws Exception {
        ServerKeys keys = new ServerKeys();
        if (createDbConnection()) {
            if (checkCollection(collectionName)) {
                collection = database.getCollection(collectionName);
                List<Document> list = (List<Document>) collection.find(new Document("keys", "serverKeys")).
                        into(new ArrayList<Document>());
                if (!list.isEmpty()) {
                    for (Document val : list) {
                        System.out.println("getting server keys if");
                        String publicKeyModules = val.getString("publicKeyModules");
                        String publicKeyExpo = val.getString("publicKeyExpo");
                        String privateKeyModules = val.getString("privateKeyModules");
                        String privateKeyExpo = val.getString("privateKeyExpo");

                        System.out.println("setting server keys");
                        keys.setPrivateKeyExpo(new BigInteger(privateKeyExpo));
                        keys.setPrivateKeyModules(new BigInteger(privateKeyModules));
                        keys.setPublicKeyExpo(new BigInteger(publicKeyExpo));
                        keys.setPublicKeyModules(new BigInteger(publicKeyModules));
                    }
                    return keys;
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean saveGenesisBlock(String collectionName, ArrayList<byte[]> data, long productID) throws Exception {
        return false;
    }

    @Override
    public boolean updateChain(ArrayList<byte[]> data, long productId, String collectionName) throws Exception {
        return false;
    }

    @Override
    public boolean getServerPrivateKeys(String collectionName) throws Exception {
        return false;
    }

    @Override
    public ArrayList<ArrayList<byte[]>> getSpecificData(long productId, String collectionName) throws Exception {
        return null;
    }
}
