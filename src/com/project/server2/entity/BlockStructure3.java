package com.project.server2.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.sql.Time;

@JsonPropertyOrder(value = {
        "blockID","date","time","quantity","totalBoxes","boxNumberRange","currentBlockHash","previousBlockHash"
})
public class BlockStructure3 implements Serializable {
    private long blockID;
    private String date;
    private Time time;
    private long quantity;
    private long totalBoxes;
    private String boxNumberRange;
    private String currentBlockHash;
    private  String previousBlockHash;

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

    public long getTotalBoxes() {
        return totalBoxes;
    }

    public void setTotalBoxes(long totalBoxes) {
        this.totalBoxes = totalBoxes;
    }

    public String getBoxNumberRange() {
        return boxNumberRange;
    }

    public void setBoxNumberRange(String boxNumberRange) {
        this.boxNumberRange = boxNumberRange;
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
