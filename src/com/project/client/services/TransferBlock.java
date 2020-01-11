package com.project.client.services;

import com.project.client.entity.BlockStructure;
import com.project.client.entity.ClientKeys;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class TransferBlock implements TransferBlockInterface {

    private CommonFunction commonFunction = new CommonFunction();

    @Override
    public String prepareBlock(long quantity,String itemName) throws Exception {
        BlockStructure block = new BlockStructure();
        block.setBlockID(generateBlockID());
        block.setTime(Time.valueOf(LocalTime.now()));
        block.setDate(Date.valueOf(LocalDate.now()));
        block.setQuantity(quantity);
        block.setItemName(itemName);
        System.out.println(commonFunction.convertJavaToJson(block));
        return commonFunction.convertJavaToJson(block);
    }

    @Override
    public String calBlockHash(String data) throws Exception {
        return commonFunction.calculateHash(data);
    }

    @Override
    public ArrayList<byte[]> encryptBlock(ClientKeys keys,String data) throws Exception {
        int count = 0;
        int start = 0, end = 0;
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
                    keys.getPrivateKeyModules(), keys.getPrivateKeyExpo());
            storeEncryptedValue.add(encryptedData);
            count++;
        }
        return storeEncryptedValue;
    }

    @Override
    public ClientKeys getKeysFromDatabase() throws Exception {
        return commonFunction.getKeysFromDB("");
    }

    @Override
    public long generateBlockID() {
        Random random = new Random();
        return 1000+random.nextInt(999);
    }

    public String manipulateBlock(String value,String hash){
        String subString = value.substring(0,value.length()-1);
        return subString+",\"currentBlockHash\":\""+hash+"\"}";
    }
}
