package com.project.server1.dao;

import java.sql.Connection;

public interface SqlDBInterface {
    boolean createDbConnection() throws Exception;

    boolean closeDbConnection(Connection connection) throws Exception;

    Long getProductID(String blockNumber) throws Exception;

    void updateBlockStatusFalse(long productID, String blockNumber) throws Exception;

    void updateBlockStatusTrue(long productID, String blockNumber) throws Exception;

    Long createNewProductID() throws Exception;

}
