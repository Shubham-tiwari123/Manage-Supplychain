package com.project.server.dao;

import java.sql.Connection;

public interface SqlDBInterface {
    boolean createDbConnection();
    boolean closeDbConnection(Connection connection);
    Long getProductID(String blockNumber);
    void getBlockNumber();
    boolean createNewProductID();
}
