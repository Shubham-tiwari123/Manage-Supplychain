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

}
