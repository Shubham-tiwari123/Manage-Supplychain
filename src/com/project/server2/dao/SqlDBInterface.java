package com.project.server2.dao;

import java.sql.Connection;

public interface SqlDBInterface {
    boolean createDbConnection();

    boolean closeDbConnection(Connection connection);

    Long getProductID(String blockNumber);

    void updateBlockStatusFalse(long productID,String blockNumber) throws Exception;

    void updateBlockStatusTrue(long productID,String blockNumber) throws Exception;

    Long createNewProductID();

}
