package com.project.server1.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class DeserializeValues implements Serializable {
    ArrayList<byte[]> encryptedData;

    public ArrayList<byte[]> getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(ArrayList<byte[]> encryptedData) {
        this.encryptedData = encryptedData;
    }
}
