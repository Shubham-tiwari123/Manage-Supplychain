package com.project.server.services;

import java.util.ArrayList;

public interface AcceptBlockInterface {

    //verify the digital signature
    boolean verifyDeviceSignature(String signature) throws Exception;

    String decryptData(ArrayList<byte[]> data) throws Exception;

    boolean verifyData(String data) throws Exception;

    String getLastBlockHashDb(long patientID) throws Exception;

    String calCurrentBlockHash(String data) throws Exception;

    String updateBlock(String lastBlockHash, String data) throws Exception;

    ArrayList<byte[]> encryptBlock(String data) throws Exception;

    boolean getServerKeys() throws Exception;

    boolean appendBlockInChain(long patientId, String data) throws Exception;
}
