package com.project.server1.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;

@JsonPropertyOrder({
        "encryptedData"
})
public class DeserializeValues implements Serializable {
    ArrayList<byte[]> encryptedData;

    public ArrayList<byte[]> getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(ArrayList<byte[]> encryptedData) {
        this.encryptedData = encryptedData;
    }
}
