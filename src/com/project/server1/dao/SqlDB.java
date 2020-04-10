package com.project.server1.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class SqlDB implements SqlDBInterface {

    private Connection connect = null;
    private Statement statement, statement1 = null;
    private ResultSet resultSet, rs1 = null;

    @Override
    public boolean createDbConnection() throws Exception {
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
    }

    @Override
    public boolean closeDbConnection(Connection connection) throws Exception {
        if (connection != null) {
            connect.close();
        }
        return false;
    }

    @Override
    public Long getProductID(String blockNumber) throws Exception {
        long productID = -1;
        String sql = null;
        if (createDbConnection()) {
            System.out.println("Database connected");
            switch (blockNumber) {
                case "block2":
                    System.out.println("block2");
                    sql = "select * from product_id where block1=true and block2=false and block3=false and block4=false";
                    break;

                case "block3":
                    System.out.println("block3");
                    sql = "select * from product_id where block2=true and block1=true and block3=false and block4=false";
                    break;

                case "block4":
                    System.out.println("block4");
                    sql = "select * from product_id where block4 = false and block1=true and block2=true and block3=true";
                    break;

            }
            if (sql == null)
                return productID;
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                productID = resultSet.getLong("product_no");
            }
            System.out.println("productsssss:" + productID);
                /*switch (blockNumber) {
                    case "block2":
                        System.out.println("2 block2");
                        sql = "update product_id set block2 = true where product_no="+productID;
                        break;

                    case "block3":
                        System.out.println("2 block3");
                        sql = "update product_id set block3 = true where product_no="+productID;
                        break;

                    case "block4":
                        System.out.println("2 block4");
                        sql = " update product_id set block4 = true where product_no="+productID;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + blockNumber);
                }
                int status = statement.executeUpdate(sql);
                System.out.println("status: "+status);
                if (status != 1)
                    productID = -1;*/
            closeDbConnection(connect);
        } else {
            System.out.println("error in connection");
        }
        return productID;
    }

    @Override
    public void updateBlockStatusTrue(long productID, String blockNumber) throws Exception {
        if (createDbConnection()) {
            String sql;
            switch (blockNumber) {
                case "block2":
                    System.out.println("2 block2");
                    sql = "update product_id set block2 = true where product_no=" + productID;
                    break;

                case "block3":
                    System.out.println("2 block3");
                    sql = "update product_id set block3 = true where product_no=" + productID;
                    break;

                case "block4":
                    System.out.println("2 block4");
                    sql = " update product_id set block4 = true where product_no=" + productID;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + blockNumber);
            }
            int status = statement.executeUpdate(sql);
            System.out.println("status: " + status);
            closeDbConnection(connect);
        }
    }

    @Override
    public void updateBlockStatusFalse(long productID, String blockNumber) throws Exception {
        if (createDbConnection()) {
            String sql;
            switch (blockNumber) {
                case "block2":
                    System.out.println("2 block2");
                    sql = "update product_id set block2 = false where product_no=" + productID;
                    break;

                case "block3":
                    System.out.println("2 block3");
                    sql = "update product_id set block3 = false where product_no=" + productID;
                    break;

                case "block4":
                    System.out.println("2 block4");
                    sql = " update product_id set block4 = false where product_no=" + productID;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + blockNumber);
            }
            int status = statement.executeUpdate(sql);
            System.out.println("status: " + status);
            closeDbConnection(connect);
        }
    }

    @Override
    public Long createNewProductID() throws Exception {
        long id = -1;
        try {
            if (createDbConnection()) {
                String searchSql = "select * from productid";
                resultSet = statement.executeQuery(searchSql);
                while (resultSet.next()) {
                    id = resultSet.getLong("product_id");
                }
                System.out.println("id:" + id);
                connect.setAutoCommit(false);
                String sql = "insert into product_id(product_no,block1, block2, block3, block4) " +
                        "values (" + id + ",true,false,false,false)";
                statement.executeUpdate(sql);
                long newID = id + 1;
                String updateSql = "update productid set product_id=" + newID;
                int status2 = statement.executeUpdate(updateSql);
                connect.commit();
                closeDbConnection(connect);
                return id;
            }
        } catch (Exception e) {
            closeDbConnection(connect);
            System.out.println("ex:" + e);
            connect.rollback();
            return id;
        }
        return id;
    }

    @Override
    public boolean verifyAndroidUserLogin(String email, String pass) throws Exception {
        boolean status = false;
        if (createDbConnection()){
            resultSet = statement.executeQuery("SELECT pass FROM registerUser WHERE email='"+email+"'");
            while (resultSet.next()){
                String password = resultSet.getString("pass");
                if(pass.equals(password))
                    status=true;
            }
        }
        closeDbConnection(connect);
        return status;
    }

    @Override
    public boolean registerAndroidUser(String email, String pass, Long phoneNumber) throws Exception {
        int status=0;
        if(createDbConnection()){
            status = statement.executeUpdate("INSERT INTO registerUser(email, pass, phoneNumber) " +
                    "VALUES ('"+email+"','"+pass+"',"+phoneNumber+")");
        }
        closeDbConnection(connect);
        return status == 1;
    }

    @Override
    public List<String> getUserDetails(String email) throws Exception {
        List<String> result = new LinkedList<>();
        if (createDbConnection()){
            resultSet = statement.executeQuery("SELECT * FROM registerUser WHERE email='"+email+"'");
            while (resultSet.next()){
                String password = resultSet.getString("pass");
                Long phoneNumber = resultSet.getLong("phoneNumber");
                result.add(email);
                result.add(password);
                result.add(String.valueOf(phoneNumber));
            }
        }
        closeDbConnection(connect);
        return result;
    }

    @Override
    public boolean registerComplain(Long productId, String shopName, String shopArea, String complainDate, String email)
            throws Exception {
        int status=0;
        if(createDbConnection()){
            status = statement.executeUpdate("insert into userComplain(productID, shopName, shopArea, complainDate, email) " +
                    "VALUES ("+productId+",'"+shopName+"','"+shopArea+"','"+complainDate+"','"+email+"')");
        }
        closeDbConnection(connect);
        return status == 1;
    }

}