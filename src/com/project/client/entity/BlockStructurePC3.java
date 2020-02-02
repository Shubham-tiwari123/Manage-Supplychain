package com.project.client.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@JsonPropertyOrder(value = {
        "blockID","date","time","quantity","totalBoxes","boxNumberRange"
})
public class BlockStructurePC3 implements Serializable {
    private long blockID;
    private Date date;
    private Time time;
    private long quantity;
    private long totalBoxes;
    private String boxNumberRange;

    public long getBlockID() {
        return blockID;
    }

    public void setBlockID(long blockID) {
        this.blockID = blockID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
