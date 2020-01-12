package com.project.client.services;

import com.project.client.dao.Database;
import com.project.client.entity.BlockStructure;
import com.project.client.entity.ClientKeys;
import com.project.client.entity.ServerKeys;
import com.project.client.utils.VariableClass;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class TransferBlock implements TransferBlockInterface {

    private CommonFunction commonFunction = new CommonFunction();
    private Database database = new Database();

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
    public ArrayList<byte[]> encryptBlock(ServerKeys keys,String data) throws Exception {
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
                    keys.getPublicKeyModules(), keys.getPublicKeyExpo());
            storeEncryptedValue.add(encryptedData);
            count++;
        }
        return storeEncryptedValue;
    }

    @Override
    public ServerKeys getKeysFromDatabase() throws Exception {
        //return database.getServerKeys(VariableClass.STORE_KEYS);
        ServerKeys keys = new ServerKeys();
        keys.setPublicKeyExpo(new BigInteger("65537"));
        keys.setPublicKeyModules(new BigInteger("41563437607600054786180423128280339278601234143" +
                "771553498036128691158253986889640865093841940882212124266613666480829074931492766380063" +
                "2638174709978126851494353288297617209883101078604836691870649734008774305513886954416973" +
                "7353879784857704425139658562565530843044838631629595936794444663233977540758132291701586" +
                "7382252705292248715053778433212820323734506982423113083109485686732649544382131126169329" +
                "0891566588382092911850568464013136291983694553432767147228375813241133088651487904383735" +
                "4032251805935744733982434094946823351261215293971619235370078207386766563513241003090760" +
                "6126887262907796954848940539716684890508970708930205702084701870224669800781922699724592" +
                "3586687789609728502356036759372152651464728304392014333368165121164222043979646530437358" +
                "8264984594667207800284393305405960764426157612868044024959964625132135588775223613878204" +
                "956629775917990376133094007028768999476925638443013347851308497785909789529885037030483"));
        return keys;
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
