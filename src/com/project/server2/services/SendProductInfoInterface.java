package com.project.server2.services;

import com.project.server2.entity.ServerKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SendProductInfoInterface {

    HashMap<Long,ArrayList<ArrayList<byte[]>>> getProductInfo(long productID) throws Exception;

    ServerKeys getServerKeys() throws Exception;

    HashMap<Long,List<String>> decryptData(HashMap<Long,ArrayList<ArrayList<byte[]>>> list, ServerKeys serverKeys) throws Exception;

    List<String> prepareDataToSend(HashMap<Long,List<String>> list) throws Exception;

}
