package com.project.server1.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.project.server1.entity.AndroidUserKeys;
import com.project.server1.entity.ClientKeys;
import com.project.server1.entity.ServerKeys;
import com.project.server1.utils.ConstantClass;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Binary;

import java.math.BigInteger;
import java.util.*;

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
        ServerAddress serverAddress = new ServerAddress(ConstantClass.IP_ADDRESS,ConstantClass.PORT_NUMBER);
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().serverSelectionTimeout(5000)
                .build();
        client = new MongoClient(serverAddress,mongoClientOptions);
        //client = new MongoClient(ConstantClass.IP_ADDRESS, ConstantClass.PORT_NUMBER);
        database = client.getDatabase(ConstantClass.DATABASE_NAME);

        /*System.setProperty("jdk.tls.trustNameService","true");
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://tiwari_23_shubham:st540362@shipchain-0yjdr.mongodb.net/test?retryWrites=true&w=majority");

        client = new MongoClient(uri);
        database  = client.getDatabase(ConstantClass.DATABASE_NAME);*/
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
    public boolean storeClientKeys(ClientKeys keys, String collectionName, String signature)
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
    public boolean storeAndroidClientKeys(AndroidUserKeys keys, String collectionName, String email)
            throws Exception {
        if (createDbConnection()) {
            if (checkCollection(collectionName)) {
                System.out.println("Storing Android keys");
                Document document = new Document("userName", email)
                        .append("publicKeyModules", keys.getPublicKeyModules().toString())
                        .append("publicKeyExpo", keys.getPublicKeyExpo().toString());
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

                        System.out.println("setting client keys");
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
    public boolean saveGenesisBlockDB(String collectionName, ArrayList<byte[]> data, long blockID)
            throws Exception {
        if (createDbConnection()) {
            if (checkCollection(collectionName)) {
                System.out.println("saving genesis block db");
                Document document = new Document("product_id", blockID)
                        .append("block", Arrays.asList(data));
                database.getCollection(collectionName).insertOne(document);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateChain(ArrayList<byte[]> data, long productId, String collectionName) throws Exception {
        if (createDbConnection()) {
            if (checkCollection(collectionName)) {
                System.out.println("appending block in chain db...");
                collection = database.getCollection(collectionName);
                Bson filter = Filters.eq("product_id", productId);
                UpdateResult result = collection.updateOne(filter,
                        Updates.addToSet("block", data));
                return result.getMatchedCount() == 1;
            }
        }
        return false;
    }

    @Override
    public boolean getServerPrivateKeys(String collectionName) throws Exception {
        return false;
    }

    @Override
    public ArrayList<ArrayList<byte[]>> getSpecificData(long productId, String collectionName) throws Exception {
        ArrayList<ArrayList<byte[]>> returnValue = new ArrayList<ArrayList<byte[]>>();
        if (createDbConnection()) {
            if (checkCollection(collectionName)) {
                System.out.println("getting patient data db....");
                collection = database.getCollection(collectionName);
                List<Document> list = (List<Document>) collection.find(new Document("product_id", productId)).
                        into(new ArrayList<Document>());
                System.out.println("list:" + list);
                for (Document doc : list) {
                    ArrayList<ArrayList<Binary>> blocks = (ArrayList<ArrayList<Binary>>) doc.get("block");
                    System.out.println(blocks.size());
                    for (ArrayList<Binary> blockList : blocks) {
                        ArrayList<byte[]> subList = new ArrayList<byte[]>();
                        for (Binary blockPart : blockList) {
                            subList.add(blockPart.getData());
                        }
                        returnValue.add(subList);
                    }
                }
            }
        }
        System.out.println("size:" + returnValue.size());
        return returnValue;
    }

    public HashMap<Long,ArrayList<ArrayList<byte[]>>> getAllProductInfo(String collectionName) throws Exception{
        HashMap<Long,ArrayList<ArrayList<byte[]>>> hashMap = new HashMap<>();
        Long productID;

        if (createDbConnection()) {
            if (checkCollection(collectionName)) {
                System.out.println("getting patient data db....");
                collection = database.getCollection(collectionName);
                List<Document> list = (List<Document>) collection.find().into(new ArrayList<Document>());
                System.out.println("list:" + list);
                for (Document doc : list) {
                    ArrayList<ArrayList<byte[]>> returnValue = new ArrayList<ArrayList<byte[]>>();
                    productID = doc.getLong("product_id");
                    System.out.println("db: product id:"+productID);
                    ArrayList<ArrayList<Binary>> blocks = (ArrayList<ArrayList<Binary>>) doc.get("block");
                    System.out.println(blocks.size());
                    for (ArrayList<Binary> blockList : blocks) {
                        ArrayList<byte[]> subList = new ArrayList<byte[]>();
                        for (Binary blockPart : blockList) {
                            subList.add(blockPart.getData());
                        }
                        returnValue.add(subList);
                    }
                    hashMap.put(productID,returnValue);
                }
            }
        }
        System.out.println("size:" + hashMap.size());
        return hashMap;
    }
}
