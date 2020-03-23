package com.project.server2.services;

import com.project.server2.dao.MongoDB;
import com.project.server2.entity.*;
import com.project.server2.utils.ConstantClass;
import org.json.simple.JSONObject;

import java.util.*;

public class SendProductInfo implements SendProductInfoInterface{

    MongoDB mongoDB = new MongoDB();

    @Override
    public HashMap<Long,ArrayList<ArrayList<byte[]>>> getProductInfo(long productID) throws Exception {
        HashMap<Long,ArrayList<ArrayList<byte[]>>> list = new HashMap<>();
        if(productID==-1){
            list = mongoDB.getAllProductInfo(ConstantClass.STORE_DATA_COLLECTION);
        }else{
            ArrayList<ArrayList<byte[]>> result= mongoDB.getSpecificData(productID,ConstantClass.STORE_DATA_COLLECTION);
            list.put(productID,result);
        }
        for (Map.Entry<Long, ArrayList<ArrayList<byte[]>>> longArrayListEntry : list.entrySet()) {
            System.out.println("key:" + ((Map.Entry) longArrayListEntry).getKey() + "  value:" + ((Map.Entry) longArrayListEntry).getValue());
        }
        return list;
    }

    @Override
    public ServerKeys getServerKeys() throws Exception {
        return mongoDB.getServerKeys(ConstantClass.STORE_KEYS);
    }

    @Override
    public HashMap<Long,List<String>> decryptData(HashMap<Long,ArrayList<ArrayList<byte[]>>> list, ServerKeys serverKeys)
            throws Exception {
        CommonFunctions commonFunctions = new CommonFunctions();
        HashMap<Long,List<String>> result = new HashMap<>();
        long productID;
        for (Map.Entry<Long, ArrayList<ArrayList<byte[]>>> longArrayListEntry : list.entrySet()) {
            List<String> stringList = new ArrayList<>();
            productID = (long) ((Map.Entry) longArrayListEntry).getKey();
            ArrayList<ArrayList<byte[]>> data = (ArrayList<ArrayList<byte[]>>) ((Map.Entry) longArrayListEntry).getValue();
            System.out.println("Decrypting data...and converting to string (file name):"+getClass());

            for(ArrayList<byte[]> val:data){
                StringBuilder builder = new StringBuilder();
                for (byte[] encryptedVal:val){
                    String subString = commonFunctions.decryptData(encryptedVal,serverKeys.getPublicKeyModules(),
                            serverKeys.getPublicKeyExpo());
                    builder.append(subString);
                }
                stringList.add(builder.toString());
            }
            result.put(productID,stringList);
        }
        return result;
    }

    @Override
    public List<String> prepareDataToSend(HashMap<Long, List<String>> list) throws Exception {
        List<String> result = new ArrayList<>();
        SendData sendData = new SendData();
        BlockStructure block1;
        BlockStructure2 block2;
        BlockStructure3 block3;
        BlockStructure4 block4;

        CommonFunctions commonFunctions = new CommonFunctions();
        for (Map.Entry<Long, List<String>> longArrayListEntry : list.entrySet()) {
            JSONObject jsonObject = new JSONObject();
            System.out.println("keyString:" + ((Map.Entry) longArrayListEntry).getKey() +
                    "  valueString:" + ((Map.Entry) longArrayListEntry).getValue());

            List<String> value = (List<String>) ((Map.Entry) longArrayListEntry).getValue();
            System.out.println("Size of list:"+value.size());
            int size = value.size();
            switch (size) {
                case 2:
                    // block1 is only present
                    block1 = commonFunctions.convertJsonToJava(value.get(1),BlockStructure.class);
                    /*jsonObject.put("productID",block1.getBlockID());
                    jsonObject.put("date",block1.getDate());
                    jsonObject.put("time",block1.getTime());
                    jsonObject.put("price",block1.getPrice());
                    jsonObject.put("qunt",block1.getQuantity());
                    jsonObject.put("supplierName",block1.getSupplierName());
                    jsonObject.put("productName",block1.getItemName());*/

                    sendData.setProductID(block1.getBlockID());
                    sendData.setCreationDate(block1.getDate());
                    sendData.setCreationTime(block1.getTime());
                    sendData.setPrice(block1.getPrice());
                    sendData.setProductName(block1.getItemName());
                    sendData.setQuantity(block1.getQuantity());
                    sendData.setSupplierName(block1.getSupplierName());
                    break;
                case 3:
                    // block2 is only present
                    /*block1 = commonFunctions.convertJsonToJava(value.get(1),BlockStructure.class);
                    jsonObject.put("productID",block1.getBlockID());
                    jsonObject.put("date",block1.getDate());
                    jsonObject.put("time",block1.getTime());
                    jsonObject.put("price",block1.getPrice());
                    jsonObject.put("qunt",block1.getQuantity());
                    jsonObject.put("supplierName",block1.getSupplierName());
                    jsonObject.put("productName",block1.getItemName());

                    block2 = commonFunctions.convertJsonToJava(value.get(2),BlockStructure2.class);
                    jsonObject.put("machineNo",block2.getMachineNumber());
                    jsonObject.put("temp",block2.getTemperature());*/

                    block1 = commonFunctions.convertJsonToJava(value.get(1),BlockStructure.class);
                    sendData.setProductID(block1.getBlockID());
                    sendData.setCreationDate(block1.getDate());
                    sendData.setCreationTime(block1.getTime());
                    sendData.setPrice(block1.getPrice());
                    sendData.setProductName(block1.getItemName());
                    sendData.setQuantity(block1.getQuantity());
                    sendData.setSupplierName(block1.getSupplierName());

                    block2 = commonFunctions.convertJsonToJava(value.get(2),BlockStructure2.class);
                    sendData.setMachineNumber(block2.getMachineNumber());
                    sendData.setTemperature(block2.getTemperature());
                    break;
                case 4:
                    // block3 is only present
                    /*block1 = commonFunctions.convertJsonToJava(value.get(1),BlockStructure.class);
                    jsonObject.put("productID",block1.getBlockID());
                    jsonObject.put("date",block1.getDate());
                    jsonObject.put("time",block1.getTime());
                    jsonObject.put("price",block1.getPrice());
                    jsonObject.put("qunt",block1.getQuantity());
                    jsonObject.put("supplierName",block1.getSupplierName());
                    jsonObject.put("productName",block1.getItemName());

                    block2 = commonFunctions.convertJsonToJava(value.get(2),BlockStructure2.class);
                    jsonObject.put("machineNo",block2.getMachineNumber());
                    jsonObject.put("temp",block2.getTemperature());

                    block3 = commonFunctions.convertJsonToJava(value.get(3),BlockStructure3.class);
                    jsonObject.put("totalBox",block3.getTotalBoxes());
                    jsonObject.put("boxRange",block3.getBoxNumberRange());*/

                    block1 = commonFunctions.convertJsonToJava(value.get(1),BlockStructure.class);
                    sendData.setProductID(block1.getBlockID());
                    sendData.setCreationDate(block1.getDate());
                    sendData.setCreationTime(block1.getTime());
                    sendData.setPrice(block1.getPrice());
                    sendData.setProductName(block1.getItemName());
                    sendData.setQuantity(block1.getQuantity());
                    sendData.setSupplierName(block1.getSupplierName());

                    block2 = commonFunctions.convertJsonToJava(value.get(2),BlockStructure2.class);
                    sendData.setMachineNumber(block2.getMachineNumber());
                    sendData.setTemperature(block2.getTemperature());

                    block3 = commonFunctions.convertJsonToJava(value.get(3),BlockStructure3.class);
                    sendData.setTotalBoxes(block3.getTotalBoxes());
                    sendData.setBoxNumberRange(block3.getBoxNumberRange());
                    break;
                case 5:
                    //block 4 is present
                    /*block1 = commonFunctions.convertJsonToJava(value.get(1),BlockStructure.class);
                    jsonObject.put("productID",block1.getBlockID());
                    jsonObject.put("date",block1.getDate());
                    jsonObject.put("time",block1.getTime());
                    jsonObject.put("price",block1.getPrice());
                    jsonObject.put("qunt",block1.getQuantity());
                    jsonObject.put("supplierName",block1.getSupplierName());
                    jsonObject.put("productName",block1.getItemName());

                    block2 = commonFunctions.convertJsonToJava(value.get(2),BlockStructure2.class);
                    jsonObject.put("machineNo",block2.getMachineNumber());
                    jsonObject.put("temp",block2.getTemperature());

                    block3 = commonFunctions.convertJsonToJava(value.get(3),BlockStructure3.class);
                    jsonObject.put("totalBox",block3.getTotalBoxes());
                    jsonObject.put("boxRange",block3.getBoxNumberRange());

                    block4 = commonFunctions.convertJsonToJava(value.get(4),BlockStructure4.class);
                    jsonObject.put("totalCarton",block4.getTotalCarton());
                    jsonObject.put("cartonRange",block4.getCartonNumber());
                    jsonObject.put("exporterName",block4.getExporterName());*/

                    block1 = commonFunctions.convertJsonToJava(value.get(1),BlockStructure.class);
                    sendData.setProductID(block1.getBlockID());
                    sendData.setCreationDate(block1.getDate());
                    sendData.setCreationTime(block1.getTime());
                    sendData.setPrice(block1.getPrice());
                    sendData.setProductName(block1.getItemName());
                    sendData.setQuantity(block1.getQuantity());
                    sendData.setSupplierName(block1.getSupplierName());

                    block2 = commonFunctions.convertJsonToJava(value.get(2),BlockStructure2.class);
                    sendData.setMachineNumber(block2.getMachineNumber());
                    sendData.setTemperature(block2.getTemperature());

                    block3 = commonFunctions.convertJsonToJava(value.get(3),BlockStructure3.class);
                    sendData.setTotalBoxes(block3.getTotalBoxes());
                    sendData.setBoxNumberRange(block3.getBoxNumberRange());

                    block4 = commonFunctions.convertJsonToJava(value.get(4),BlockStructure4.class);
                    sendData.setTotalCarton(block4.getTotalCarton());
                    sendData.setCartonNumberRange(block4.getCartonNumber());
                    sendData.setExporterName(block4.getExporterName());
                    break;
            }
            sendData.setBlockPresent(size-1);
            String jsonString = commonFunctions.convertJavaToJson(sendData);
            /*long key = (long) ((Map.Entry) longArrayListEntry).getKey();
            jsonObject.put("blocksPresent",size-1);
            resultObj.put(key,jsonObject);*/
            result.add(jsonString);
        }
        return result;
    }
}
