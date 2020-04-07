package com.project.server1.dao;

import java.sql.Connection;
import java.util.List;

public interface SqlDBInterface {
    boolean createDbConnection() throws Exception;

    boolean closeDbConnection(Connection connection) throws Exception;

    Long getProductID(String blockNumber) throws Exception;

    void updateBlockStatusFalse(long productID, String blockNumber) throws Exception;

    void updateBlockStatusTrue(long productID, String blockNumber) throws Exception;

    Long createNewProductID() throws Exception;

    boolean verifyAndroidUserLogin(String email,String pass) throws Exception;

    boolean registerAndroidUser(String email,String pass,Long phoneNumber) throws Exception;

    List<String> getUserDetails(String email) throws Exception;

    boolean registerComplain(Long productId, String shopName, String shopArea, String complainDate, String email)
            throws Exception;

}
