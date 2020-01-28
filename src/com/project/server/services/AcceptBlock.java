package com.project.server.services;

import com.project.server.dao.Database;
import com.project.server.entity.BlockStructure;
import com.project.server.entity.ClientKeys;
import com.project.server.utils.VariableClass;

import java.util.ArrayList;

public class AcceptBlock implements AcceptBlockInterface {

    private CommonFunctions commonFunctions = new CommonFunctions();
    private Database database = new Database();

    @Override
    public boolean verifyDeviceSignature(String signature) throws Exception {
        return false;
    }

    @Override
    public String decryptData(ArrayList<byte[]> data, ClientKeys keys) throws Exception {
        ArrayList<String> storeDecryptData = new ArrayList<String>();
        for (byte[] datum : data) {
            String decryptedData = commonFunctions.decryptData(datum, keys.getClientPubKeyMod(),
                    keys.getClientPubKeyExpo());
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
        BlockStructure blockStructure = commonFunctions.convertJsonToJava(data,BlockStructure.class);
        String blockString = commonFunctions.convertJavaToJson(blockStructure);
        System.out.println("block:"+blockString);
        String hash = calCurrentBlockHash(blockString);
        return hash.equals(currentBlockHash);
    }

    @Override
    public String getLastBlockHashDb(long patientID) throws Exception {
        return null;
    }

    @Override
    public String calCurrentBlockHash(String data) throws Exception {
        return commonFunctions.calculateHash(data);
    }

    @Override
    public String updateBlock(String lastBlockHash, String data) throws Exception {
        return null;
    }

    @Override
    public ArrayList<byte[]> encryptBlock(String data) throws Exception {
        return null;
    }

    @Override
    public ClientKeys getClientKeys(String signature) throws Exception {
        return database.getClientKeys(VariableClass.STORE_KEYS,signature);
    }

    @Override
    public boolean appendBlockInChain(long patientId, String data) throws Exception {
        return false;
    }
}
