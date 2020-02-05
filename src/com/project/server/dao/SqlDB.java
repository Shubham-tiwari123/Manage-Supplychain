package com.project.server.dao;

import java.sql.*;
import java.util.Properties;

public class SqlDB implements SqlDBInterface {

    private Connection connect = null;
    private Statement statement,statement1 = null;
    protected ResultSet resultSet,rs1 = null;

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
        }catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public boolean closeDbConnection(Connection connection){
        if (connection != null) {
            try {
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
    public void getProductID() {
        try {
            if (createDbConnection()) {
                System.out.println("Database connected");
                resultSet = statement.executeQuery("select * from productid");
                while (resultSet.next()){
                    System.out.println(resultSet.getString("product_id"));
                }
                closeDbConnection(connect);
            } else {
                System.out.println("error in connection");
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    //+237682501979
    @Override
    public void getBlockNumber() {

    }
}
