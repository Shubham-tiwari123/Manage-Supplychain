package com.project.server.dao;

import java.sql.*;
import java.util.Properties;

public class SqlDB implements SqlDBInterface {

    private Connection connect = null;
    private Statement statement, statement1 = null;
    private ResultSet resultSet, rs1 = null;

    @Override
    public boolean createDbConnection() {
        try {
            String dbClassName = "com.mysql.cj.jdbc.Driver";
            Class.forName(dbClassName);
            Properties property = new Properties();
            property.put("user", "shipchain");
            property.put("password", "passpass");
            String CONNECTION = "jdbc:mysql://127.0.0.1/shipchainServer";
            connect = DriverManager.getConnection(CONNECTION, property);
            statement = connect.createStatement();
            statement1 = connect.createStatement();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public boolean closeDbConnection(Connection connection) {
        if (connection != null) {
            try {
                System.out.println("Closing connection");
                connect.close();
                return true;
            } catch (SQLException e) {
                System.out.println("Connection can not be ade free: " + e);
                return false;
            }
        }
        return false;
    }

    @Override
    public Long getProductID(String blockNumber) {
        long productID = -1;
        String sql;
        try {
            if (createDbConnection()) {
                System.out.println("Database connected");
                switch (blockNumber) {
                    case "block2":
                        sql = "select * from product_id where block1=true and block2=false and block3=false and block4=false";
                        break;

                    case "block3":
                        sql = "select * from product_id where block2=true and block1=true and block3=false and block4=false";
                        break;

                    case "block4":
                        sql = "select * from product_id where block4 = false and block1=true and block2=true and block3=true";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + blockNumber);
                }
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    productID = resultSet.getLong("product_no");
                }

                switch (blockNumber) {
                    case "block2":
                        sql = "update product_id set block2 = true where block1=true and block3=false and block4=false";
                        break;

                    case "block3":
                        sql = "update product_id set block3 = true where block1=true and block2=true and block4=false";
                        break;

                    case "block4":
                        sql = " update product_id set block4 = true where block1=true and block2=true and block3=true";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + blockNumber);
                }
                int status = statement.executeUpdate(sql);
                if (status != 1)
                    productID = -1;
                closeDbConnection(connect);
            } else {
                System.out.println("error in connection");
            }
            return productID;
        } catch (Exception ex) {
            closeDbConnection(connect);
            System.out.println(ex);
            return productID;
        }
    }

    @Override
    public void getBlockNumber() {

    }

    @Override
    public boolean createNewProductID() {
        try {
            if (createDbConnection()) {
                String searchSql = "select * from productid";
                resultSet = statement.executeQuery(searchSql);
                long id = 0;
                while (resultSet.next()) {
                    id = resultSet.getLong("product_id");
                }
                System.out.println("id:" + id);
                connect.setAutoCommit(false);
                String sql = "insert into product_id(product_no,block1, block2, block3, block4) " +
                        "values (" + id + ",true,false,false,false)";
                int status1 = statement.executeUpdate(sql);
                long newID = id + 1;
                String updateSql = "update productid set product_id=" + newID;
                int status2 = statement.executeUpdate(updateSql);
                connect.commit();
                closeDbConnection(connect);
                return (status1 == 1 && status2 == 1);
            }
        } catch (Exception e) {
            closeDbConnection(connect);
            System.out.println("ex:" + e);
            try {
                connect.rollback();
                return false;
            } catch (SQLException ex) {
                return false;
            }
        }
        return false;
    }

}