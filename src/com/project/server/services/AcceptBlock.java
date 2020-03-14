package com.project.server.services;

import com.project.server.dao.MongoDB;
import com.project.server.entity.*;
import com.project.server.utils.ConstantClass;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AcceptBlock implements AcceptBlockInterface {

    private CommonFunctions commonFunctions = new CommonFunctions();
    private MongoDB database = new MongoDB();

    @Override
    public boolean verifyDeviceSignature(String signature) throws Exception {
        return false;
    }

    @Override
    public String decryptData(ArrayList<byte[]> data, ServerKeys keys) throws Exception {
        ArrayList<String> storeDecryptData = new ArrayList<String>();
        for (byte[] datum : data) {
            String decryptedData = commonFunctions.decryptData(datum, keys.getPrivateKeyModules(),
                    keys.getPrivateKeyExpo());
            storeDecryptData.add(decryptedData);
        }
        StringBuilder builder = new StringBuilder();
        for (String val : storeDecryptData) {
            builder.append(val);
        }
        return builder.toString();
    }

    @Override
    public boolean verifyData(String data,String currentBlockHash) throws Exception {
        BlockStructure blockStructure = commonFunctions.convertJsonToJava(data, BlockStructure.class);
        blockStructure.setCurrentBlockHash("0");
        String blockString = commonFunctions.convertJavaToJson(blockStructure);
        System.out.println("block:"+blockString);
        String hash = calCurrentBlockHash(blockString);
        return hash.equals(currentBlockHash);
    }

    public boolean verifyData2(String data,String currentBlockHash) throws Exception {
        BlockStructure2 blockStructure = commonFunctions.convertJsonToJava(data, BlockStructure2.class);
        blockStructure.setCurrentBlockHash("0");
        String blockString = commonFunctions.convertJavaToJson(blockStructure);
        System.out.println("block:"+blockString);
        String hash = calCurrentBlockHash(blockString);
        System.out.println("cur hash:-"+hash);
        return hash.equals(currentBlockHash);
    }

    public boolean verifyData3(String data,String currentBlockHash) throws Exception {
        BlockStructure3 blockStructure = commonFunctions.convertJsonToJava(data, BlockStructure3.class);
        blockStructure.setCurrentBlockHash("0");
        String blockString = commonFunctions.convertJavaToJson(blockStructure);
        System.out.println("block:"+blockString);
        String hash = calCurrentBlockHash(blockString);
        return hash.equals(currentBlockHash);
    }

    public boolean verifyData4(String data,String currentBlockHash) throws Exception {
        BlockStructure4 blockStructure = commonFunctions.convertJsonToJava(data, BlockStructure4.class);
        blockStructure.setCurrentBlockHash("0");
        String blockString = commonFunctions.convertJavaToJson(blockStructure);
        System.out.println("block:"+blockString);
        String hash = calCurrentBlockHash(blockString);
        return hash.equals(currentBlockHash);
    }

    @Override
    public List getLastBlockHashDb(long productID) throws Exception {
        ArrayList<ArrayList<byte[]>> dataFromDb = database.getSpecificData(productID,
                ConstantClass.STORE_DATA_COLLECTION);

        String chain = convertEncryptedData(dataFromDb.get(dataFromDb.size()-1),
                database.getServerKeys(ConstantClass.STORE_KEYS));

        System.out.println("chains:"+chain);
        String lastBlockHash = null;
        if (dataFromDb.size() == 2) {
             BlockStructure block1 = commonFunctions.convertJsonToJava(chain, BlockStructure.class);
             lastBlockHash = block1.getCurrentBlockHash();
        } else if (dataFromDb.size() == 3) {
            BlockStructure2 block2 = commonFunctions.convertJsonToJava(chain, BlockStructure2.class);
            lastBlockHash = block2.getCurrentBlockHash();
        } else if (dataFromDb.size() == 4) {
            BlockStructure3 block3 = commonFunctions.convertJsonToJava(chain, BlockStructure3.class);
            lastBlockHash = block3.getCurrentBlockHash();
        } else if (dataFromDb.size() == 5) {
            BlockStructure4 block4 = commonFunctions.convertJsonToJava(chain, BlockStructure4.class);
            lastBlockHash = block4.getCurrentBlockHash();
        }
        System.out.println("last:"+lastBlockHash);
        List list = new ArrayList();
        list.add(lastBlockHash);
        list.add(dataFromDb.size());
        return list;
    }

    @Override
    public String calCurrentBlockHash(String data) throws Exception {
        return commonFunctions.calculateHash(data);
    }

    @Override
    public String updateBlock(List blockData, String data) throws Exception {
        System.out.println("updating block");
        String lastBlockHash = (String) blockData.get(0);
        int listSize = (int) blockData.get(1);

        if (listSize == 2) {
            System.out.println("block 1 if");
            BlockStructure2 block2 = commonFunctions.convertJsonToJava(data, BlockStructure2.class);
            block2.setPreviousBlockHash(lastBlockHash);
            String convertObj = commonFunctions.convertJavaToJson(block2);
            System.out.println("previous added:"+convertObj);
            // re-calculate hash value
            String newHashOfBlock = calCurrentBlockHash(convertObj);
            // create new block and insert current hash
            block2.setCurrentBlockHash(newHashOfBlock);
            return commonFunctions.convertJavaToJson(block2);
        }
        else if (listSize == 3) {
            System.out.println("block 2 if");
            BlockStructure3 block3 = commonFunctions.convertJsonToJava(data, BlockStructure3.class);
            block3.setPreviousBlockHash(lastBlockHash);
            String convertObj = commonFunctions.convertJavaToJson(block3);
            System.out.println("previous added:"+convertObj);
            // re-calculate hash value
            String newHashOfBlock = calCurrentBlockHash(convertObj);
            // create new block and insert current hash
            block3.setCurrentBlockHash(newHashOfBlock);
            return commonFunctions.convertJavaToJson(block3);
        }
        else {
            System.out.println("block 3 if");
            BlockStructure4 block4 = commonFunctions.convertJsonToJava(data, BlockStructure4.class);
            block4.setPreviousBlockHash(lastBlockHash);
            String convertObj = commonFunctions.convertJavaToJson(block4);
            System.out.println("previous added:"+convertObj);
            // re-calculate hash value
            String newHashOfBlock = calCurrentBlockHash(convertObj);
            // create new block and insert current hash
            block4.setCurrentBlockHash(newHashOfBlock);
            return commonFunctions.convertJavaToJson(block4);
        }
        /*else {
            System.out.println("block 4 if");
            BlockStructure4 block4 = commonFuncti lastBlockHash = block3.getCurrentBlockHash();ons.convertJsonToJava(data, BlockStructure4.class);
            lastBlockHash = block4.getCurrentBlockHash();
            block4.setPreviousBlockHash(lastBlockHash);
            lastBlockHash = block4.getCurrentBlockHash();
            String convertObj = commonFunctions.convertJavaToJson(block4);
            System.out.println("previous added:"+convertObj);
            // re-calculate hash value
            String newHashOfBlock = calCurrentBlockHash(convertObj);
            // create new block and insert current hash
            block4.setCurrentBlockHash(newHashOfBlock);
            return commonFunctions.convertJavaToJson(block4);
        }*/
    }

    @Override
    public ArrayList<byte[]> encryptBlock(String data) throws Exception {
        System.out.println("encrypting genesis block....");
        int count = 0;
        int start = 0, end = 0;
        String substring;
        ArrayList<String> storeSubString = new ArrayList<String>();
        ArrayList<byte[]> storeEncryptedValue = new ArrayList<byte[]>();
        while (count <= data.length()) {
            if (data.length() - end > 250) {
                end = start + 251;
                substring = data.substring(start, end);
                storeSubString.add(substring);
                start = start + 251;
            } else {
                start = end;
                end = data.length();
                substring = data.substring(start, end);
                storeSubString.add(substring);
            }
            count = count + 250;
        }
        count = 0;
        ServerKeys serverKey = database.getServerKeys(ConstantClass.STORE_KEYS);

        while (count != storeSubString.size()) {
            byte[] encryptedData =  commonFunctions.encryptData(storeSubString.get(count),
                    serverKey.getPrivateKeyModules(), serverKey.getPrivateKeyExpo());

            storeEncryptedValue.add(encryptedData);
            count++;
        }
        return storeEncryptedValue;
    }

    @Override
    public ClientKeys getClientKeys(String signature) throws Exception {
        return database.getClientKeys(ConstantClass.STORE_KEYS,signature);
    }

    @Override
    public boolean appendBlockInChain(long productID, String data) throws Exception {
        ArrayList<byte[]> encryptedValue = encryptBlock(data);
        return database.updateChain(encryptedValue, productID,ConstantClass.STORE_DATA_COLLECTION);
    }

    @Override
    public GenesisBlock createGenesisBlock(long generatedID) throws Exception {
        System.out.println("creating genesis block.....");
        GenesisBlock block = new GenesisBlock();
        block.setId(generatedID);
        block.setCreationDate(Date.valueOf(LocalDate.now()));
        block.setCreationTime(Time.valueOf(LocalTime.now()));
        block.setCompanyName("shipChain");
        block.setPreviousBlockHash("shubhamA56vidyanshuA23abhishekA1akshayA21");
        block.setCurrentBlockHash("0");
        String jsonString = commonFunctions.convertJavaToJson(block);
        block.setCurrentBlockHash(calCurrentBlockHash(jsonString));
        System.out.println("current hash:"+block.getCurrentBlockHash());
        return block;
    }

    @Override
    public boolean storeGenesisBlock(GenesisBlock block, long blockID) throws Exception{
        System.out.println("storing genesis block");
        String data = commonFunctions.convertJavaToJson(block);
        System.out.println("data:\n  "+data);
        ArrayList<byte[]> encryptedData = encryptBlock(data);
        return database.saveGenesisBlockDB(ConstantClass.STORE_DATA_COLLECTION,encryptedData,
                blockID);
    }

    @Override
    public boolean storeProductBlockA(String block, long blockID,String previousBlockHash) throws Exception {
        BlockStructure blockStructure = commonFunctions.convertJsonToJava(block, BlockStructure.class);
        blockStructure.setPreviousBlockHash(previousBlockHash);
        System.out.println("storing product block A");
        String data = commonFunctions.convertJavaToJson(blockStructure);
        System.out.println("data:\n  "+data);
        ArrayList<byte[]> encryptedData = encryptBlock(data);
        return database.updateChain(encryptedData, blockID,ConstantClass.STORE_DATA_COLLECTION);
    }

    @Override
    public String convertEncryptedData(ArrayList<byte[]> data, ServerKeys serverKeys) throws Exception{
        StringBuilder builder = new StringBuilder();
        for (byte[] byteValue : data) {
            String val = commonFunctions.decryptData(byteValue, serverKeys.getPublicKeyModules(),
                    serverKeys.getPublicKeyExpo());
            builder.append(val);
        }
        return builder.toString();
    }

    @Override
    public ServerKeys getKeysFromDatabase() throws Exception {
        return database.getServerKeys(ConstantClass.STORE_KEYS);
    }
}
