package com.example.shipchain.entity;

import java.sql.Time;

public class SendData {

    private long productID;
    private String creationDate;
    private String creationTime;
    private long quantity;
    private String productName;
    private String supplierName;
    private long price;
    private long machineNumber;
    private long temperature;
    private long totalBoxes;
    private String boxNumberRange;
    private long totalCarton;
    private String cartonNumberRange;
    private String exporterName;
    private int blockPresent;

    public int getBlockPresent() {
        return blockPresent;
    }

    public void setBlockPresent(int blockPresent) {
        this.blockPresent = blockPresent;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
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

    public long getTotalCarton() {
        return totalCarton;
    }

    public void setTotalCarton(long totalCarton) {
        this.totalCarton = totalCarton;
    }

    public String getCartonNumberRange() {
        return cartonNumberRange;
    }

    public void setCartonNumberRange(String cartonNumberRange) {
        this.cartonNumberRange = cartonNumberRange;
    }

    public String getExporterName() {
        return exporterName;
    }

    public void setExporterName(String exporterName) {
        this.exporterName = exporterName;
    }
}
