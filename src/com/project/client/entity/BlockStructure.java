package com.project.client.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@JsonPropertyOrder(value = {
        "blockID","date","time","quantity","itemName","price","supplierName","currentBlockHash","previousBlockHash"
})
public class BlockStructure implements Serializable {
    private long blockID;
    private String date;
    private Time time;
    private long quantity;
    private String itemName;
    private long price;
    private String supplierName;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
