package com.project.server.services;

import com.project.server.entity.ClientKeys;

import java.util.ArrayList;

public interface AcceptBlockInterface {

    //verify the digital signature
    boolean verifyDeviceSignature(String signature) throws Exception;

    String decryptData(ArrayList<byte[]> data, ClientKeys keys) throws Exception;

    boolean verifyData(String data,String currentBlockHash) throws Exception;

    String getLastBlockHashDb(long patientID) throws Exception;

    String calCurrentBlockHash(String data) throws Exception;

    String updateBlock(String lastBlockHash, String data) throws Exception;

    ArrayList<byte[]> encryptBlock(String data) throws Exception;

    ClientKeys getClientKeys(String signature) throws Exception;

    boolean appendBlockInChain(long patientId, String data) throws Exception;


}
