package com.project.server.services;

import com.project.server.entity.ClientKeys;
import com.project.server.entity.ServerKeys;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SendProductInfoInterface {

    HashMap<Long,ArrayList<ArrayList<byte[]>>> getProductInfo(long productID) throws Exception;

    ServerKeys getServerKeys() throws Exception;

    HashMap<Long,List<String>> decryptData(HashMap<Long,ArrayList<ArrayList<byte[]>>> list, ServerKeys serverKeys) throws Exception;

    List<String> prepareDataToSend(HashMap<Long,List<String>> list) throws Exception;

}
