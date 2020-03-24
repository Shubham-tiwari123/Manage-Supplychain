package com.project.client.services;

import com.project.client.dao.Database;
import com.project.client.entity.*;
import com.project.client.utils.ConstantClass;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

public class TransferBlock implements TransferBlockInterface {

    private CommonFunction commonFunction = new CommonFunction();
    private Database database = new Database();

    @Override
    public String prepareBlock(long blockID, long quantity,String itemName,String supplierName,long price,String date)
            throws Exception {
        BlockStructure block = new BlockStructure();
        block.setBlockID(blockID);
        block.setTime(Time.valueOf(LocalTime.now()));
        block.setDate(date);
        block.setQuantity(quantity);
        block.setItemName(itemName);
        block.setSupplierName(supplierName);
        block.setPrice(price);
        block.setCurrentBlockHash("0");
        block.setPreviousBlockHash("0");

        String hash = calBlockHash(commonFunction.convertJavaToJson(block));
        block.setCurrentBlockHash(hash);
        System.out.println(commonFunction.convertJavaToJson(block));
        return commonFunction.convertJavaToJson(block);
    }

    public String prepareBlock2(long blockID, long quantity,long temp,long machineNo,String date)
            throws Exception {
        BlockStructurePC2 block = new BlockStructurePC2();
        block.setBlockID(blockID);
        block.setTime(Time.valueOf(LocalTime.now()));
        block.setDate(date);
        block.setQuantity(quantity);
        block.setTemperature(temp);
        block.setMachineNumber(machineNo);
        block.setCurrentBlockHash("0");
        block.setPreviousBlockHash("0");

        String hash = calBlockHash(commonFunction.convertJavaToJson(block));
        System.out.println("hashhh:"+hash);
        block.setCurrentBlockHash(hash);
        System.out.println(commonFunction.convertJavaToJson(block));
        return commonFunction.convertJavaToJson(block);
    }

    public String prepareBlock3(long blockID, long quantity,String boxRange,long totalBoxes,String date)
            throws Exception {
        BlockStructurePC3 block = new BlockStructurePC3();
        block.setBlockID(blockID);
        block.setTime(Time.valueOf(LocalTime.now()));
        block.setDate(date);
        block.setQuantity(quantity);
        block.setBoxNumberRange(boxRange);
        block.setTotalBoxes(totalBoxes);
        block.setCurrentBlockHash("0");
        block.setPreviousBlockHash("0");

        String hash = calBlockHash(commonFunction.convertJavaToJson(block));
        System.out.println("hashhh:"+hash);
        block.setCurrentBlockHash(hash);
        System.out.println(commonFunction.convertJavaToJson(block));
        return commonFunction.convertJavaToJson(block);
    }

    public String prepareBlock4(long blockID, long totalCarton,String exporterName,String cartonNumber,String date)
            throws Exception {
        BlockStructurePC4 block = new BlockStructurePC4();
        block.setBlockID(blockID);
        block.setTime(Time.valueOf(LocalTime.now()));
        block.setDate(date);
        block.setTotalCarton(totalCarton);
        block.setExporterName(exporterName);
        block.setCartonNumber(cartonNumber);
        block.setCurrentBlockHash("0");
        block.setPreviousBlockHash("0");

        String hash = calBlockHash(commonFunction.convertJavaToJson(block));
        block.setCurrentBlockHash(hash);
        System.out.println(commonFunction.convertJavaToJson(block));
        return commonFunction.convertJavaToJson(block);
    }

    @Override
    public String calBlockHash(String data) throws Exception {
        return commonFunction.calculateHash(data);
    }

    @Override
    public ArrayList<byte[]> encryptBlock(ServerKeys keys,String data) throws Exception {
        int count = 0;
        int start = 0, end = 0;
        System.out.println("datassss:"+data.getBytes(StandardCharsets.UTF_8).length);
        String substring;
        ArrayList<String> storeSubString = new ArrayList<String>();
        ArrayList<byte[]> storeEncryptedValue = new ArrayList<byte[]>();
        while (count <= data.length()) {
            if (data.length() - end > 250) {
                end = start + 251;
                substring = data.substring(start, end);
                storeSubString.add(substring);
                start = start + 251;
            } else {
                start = end;
                end = data.length();
                substring = data.substring(start, end);
                storeSubString.add(substring);
            }
            count = count + 250;
        }
        count = 0;
        while (count != storeSubString.size()) {
            byte[] encryptedData = commonFunction.encryptData(storeSubString.get(count),
                    keys.getPublicKeyModules(), keys.getPublicKeyExpo());
            storeEncryptedValue.add(encryptedData);
            count++;
        }
        return storeEncryptedValue;
    }

    @Override
    public ServerKeys getKeysFromDatabase() throws Exception {
        return database.getServerKeys(ConstantClass.STORE_KEYS);
        /*ServerKeys keys = new ServerKeys();
        return keys;*/
    }

    public String manipulateBlock(String value,String hash){
        String subString = value.substring(0,value.length()-1);
        return subString+",\"currentBlockHash\":\""+hash+"\"}";
    }
}
