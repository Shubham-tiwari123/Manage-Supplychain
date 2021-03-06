package com.project.server1.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.sql.Time;

@JsonPropertyOrder(value = {
        "blockID","date","time","quantity","machineNumber","temperature","currentBlockHash","previousBlockHash"
})
public class BlockStructure2 implements Serializable {
    private long blockID;
    private String date;
    private Time time;
    private long quantity;
    private long machineNumber;
    private long temperature;
    private String currentBlockHash;
    private String previousBlockHash;

    public long getBlockID() {
        return blockID;
    }

    public void setBlockID(long blockID) {
        this.blockID = blockID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(long machineNumber) {
        this.machineNumber = machineNumber;
    }

    public long getTemperature() {
        return temperature;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }

    public String getCurrentBlockHash() {
        return currentBlockHash;
    }

    public void setCurrentBlockHash(String currentBlockHash) {
        this.currentBlockHash = currentBlockHash;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }
}
