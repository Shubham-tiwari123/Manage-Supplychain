package com.project.server2.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.sql.Time;

@JsonPropertyOrder(value = {
        "blockID","date","time","totalCarton","cartonNumber","exporterName","currentBlockHash","previousBlockHash"
})
public class BlockStructure4 implements Serializable {
    private long blockID;
    private String date;
    private Time time;
    private long totalCarton;
    private String cartonNumber;
    private String exporterName;
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

    public long getTotalCarton() {
        return totalCarton;
    }

    public void setTotalCarton(long totalCarton) {
        this.totalCarton = totalCarton;
    }

    public String getCartonNumber() {
        return cartonNumber;
    }

    public void setCartonNumber(String cartonNumber) {
        this.cartonNumber = cartonNumber;
    }

    public String getExporterName() {
        return exporterName;
    }

    public void setExporterName(String exporterName) {
        this.exporterName = exporterName;
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
