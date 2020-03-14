package com.project.server.services;

import com.project.server.entity.ClientKeys;

import java.util.ArrayList;
import java.util.List;

public interface SendBlockInterface {

    //verify the digital signature
    boolean verifyDeviceSignature(String signature) throws Exception;

    List<String> getDataDB(long patientID) throws Exception;

    ClientKeys getClientKeys(String signature) throws Exception;

    ArrayList<ArrayList<byte[]>> encryptDataAgain(ClientKeys keys, List<String> data) throws Exception;
}
