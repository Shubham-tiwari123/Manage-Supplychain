package com.project.server1.services;

import com.project.server1.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface AndroidFunctionInterface {

    boolean verifyUserLogin(String email,String password) throws Exception;

    boolean registerUser(RegisterUser registerUser) throws Exception;

    RegisterUser getUserDetails(String email) throws Exception;

    boolean registerComplain(RegisterComplain complain) throws Exception;

    AndroidUserKeys generateKeys(String email) throws Exception;

    AndroidUserKeys getKeys(String email) throws Exception;

    boolean storeKeys(String email,AndroidUserKeys keys) throws Exception;

    ArrayList<ArrayList<byte[]>> getProductInfo(long productID) throws Exception;

    ServerKeys getServerKeys() throws Exception;

    List<String> decryptData(ArrayList<ArrayList<byte[]>> list, ServerKeys serverKeys) throws Exception;

    SendData prepareDataToSend(List<String> list) throws Exception;

}
