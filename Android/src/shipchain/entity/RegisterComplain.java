package com.example.shipchain.entity;

import java.io.Serializable;

public class RegisterComplain implements Serializable {

    private Long productID;
    private String shopName;
    private String shopArea;
    private String complainDate;
    private String email;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopArea() {
        return shopArea;
    }

    public void setShopArea(String shopArea) {
        this.shopArea = shopArea;
    }

    public String getComplainDate() {
        return complainDate;
    }

    public void setComplainDate(String complainDate) {
        this.complainDate = complainDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
