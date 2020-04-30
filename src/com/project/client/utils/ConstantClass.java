package com.project.client.utils;

public final class ConstantClass {

    final static public String DATABASE_NAME = "shipChainClient";
    final static public String STORE_KEYS = "storeClientKeys";
    final static public String IP_ADDRESS = "127.0.0.1";
    final static public int PORT_NUMBER = 27017;
    final static public String SOCKET_ADDRESS = "127.0.0.1";
    final static public int SOCKET_PORT_NUMBER = 4000;

    static public boolean SERVER1_STATUS = true;
    static public boolean SERVER2_STATUS = true;

    static public int SERVER1_TRY = 0;
    static public int SERVER2_TRY = 0;

    final static public int SUCCESSFUL = 200;
    final static public int FAILED = 400;
    final static public int BAD_REQUEST = 401;
    final static public int INTERNAL_SERVER_ERROR = 500;

    final static public String GET_PRODUCT_ID_URL1 = "http://localhost:8081/request_productID";
    final static public String GET_PRODUCT_ID_URL2 = "http://localhost:8082/request_productID";

    final static public String SEND_BLOCK1_URL1 = "http://localhost:8081/first_block";
    final static public String SEND_BLOCK1_URL2 = "http://localhost:8082/first_block";

    final static public String SEND_BLOCK2_URL1 = "http://localhost:8081/second_block";
    final static public String SEND_BLOCK2_URL2 = "http://localhost:8082/second_block";

    final static public String SEND_BLOCK3_URL1 = "http://localhost:8081/third_block";
    final static public String SEND_BLOCK3_URL2 = "http://localhost:8082/third_block";

    final static public String SEND_BLOCK4_URL1 = "http://localhost:8081/fourth_block";
    final static public String SEND_BLOCK4_URL2 = "http://localhost:8082/fourth_block";

    final static public String CONNECT_SERVER_URL1 = "http://localhost:8081/connect_server";
    final static public String CONNECT_SERVER_URL2 = "http://localhost:8082/connect_server";
}
