package com.project.server.services;

import com.project.server.entity.ClientKeys;
import com.project.server.entity.GenesisBlock;
import com.project.server.entity.ServerKeys;

import java.util.ArrayList;
import java.util.List;

public interface AcceptBlockInterface {

    //verify the digital signature
    boolean verifyDeviceSignature(String signature) throws Exception;

    String decryptData(ArrayList<byte[]> data, ServerKeys keys) throws Exception;

    boolean verifyData(String data, String currentBlockHash) throws Exception;

    List getLastBlockHashDb(long patientID) throws Exception;

    String calCurrentBlockHash(String data) throws Exception;

    String updateBlock(List blockData, String data) throws Exception;

    ArrayList<byte[]> encryptBlock(String data) throws Exception;

    ClientKeys getClientKeys(String signature) throws Exception;

    boolean appendBlockInChain(long patientId, String data) throws Exception;

    GenesisBlock createGenesisBlock(long generatedID) throws Exception;

    boolean storeGenesisBlock(GenesisBlock block, long blockID) throws Exception;

    boolean storeProductBlockA(String block, long blockID, String previousBlockHash) throws Exception;

    String convertEncryptedData(ArrayList<byte[]> data, ServerKeys serverKeys) throws Exception;

    ServerKeys getKeysFromDatabase() throws Exception;

}
