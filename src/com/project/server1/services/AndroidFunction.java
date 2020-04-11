package com.project.server1.services;

import com.project.server1.dao.MongoDB;
import com.project.server1.dao.SqlDB;
import com.project.server1.entity.*;
import com.project.server1.utils.ConstantClass;

import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.List;

public class AndroidFunction implements AndroidFunctionInterface {

    MongoDB mongoDB = new MongoDB();
    CommonFunctions commonFunctions = new CommonFunctions();
    SqlDB sqlDB = new SqlDB();

    @Override
    public boolean verifyUserLogin(String email, String password) throws Exception {
        return sqlDB.verifyAndroidUserLogin(email, password);
    }

    @Override
    public boolean registerUser(RegisterUser registerUser) throws Exception {
        return sqlDB.registerAndroidUser(registerUser.getEmail(), registerUser.getPassword(), registerUser.getPhoneNumber());
    }

    @Override
    public RegisterUser getUserDetails(String email) throws Exception {
        List<String> details = sqlDB.getUserDetails(email);
        RegisterUser getDetails = new RegisterUser();
        getDetails.setEmail(details.get(0));
        getDetails.setPassword(details.get(1));
        getDetails.setPhoneNumber(Long.valueOf(details.get(2)));
        return getDetails;
    }

    @Override
    public boolean registerComplain(RegisterComplain complain) throws Exception {
        return sqlDB.registerComplain(complain.getProductID(), complain.getShopName(), complain.getShopArea(),
                complain.getComplainDate(), complain.getEmail());
    }

    @Override
    public AndroidUserKeys generateKeys(String email) throws Exception {
        AndroidUserKeys userKeys = new AndroidUserKeys();
        System.out.println("generating keys: " + getClass());
        KeyPairGenerator keyPair = KeyPairGenerator.getInstance("RSA");
        keyPair.initialize(3072);//3072
        KeyPair pair = keyPair.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        // Generating key-pair
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec rsaPublicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        RSAPrivateKeySpec rsaPrivateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
        //Setting public keys
        userKeys.setPublicKeyExpo(rsaPublicKeySpec.getPublicExponent());
        userKeys.setPublicKeyModules(rsaPublicKeySpec.getModulus());
        //Setting private keys
        userKeys.setPrivateKeyExpo(rsaPrivateKeySpec.getPrivateExponent());
        userKeys.setPrivateKeyModules(rsaPrivateKeySpec.getModulus());
        return userKeys;
    }

    @Override
    public AndroidUserKeys getKeys(String email) throws Exception {
        return mongoDB.getAndroidClientKeys(ConstantClass.STORE_KEYS,email);
    }

    @Override
    public boolean storeKeys(String email, AndroidUserKeys keys) throws Exception {
        return mongoDB.storeAndroidClientKeys(keys, ConstantClass.STORE_KEYS, email);
    }

    @Override
    public ArrayList<ArrayList<byte[]>> getProductInfo(long productID) throws Exception {
        return mongoDB.getSpecificData(productID, ConstantClass.STORE_DATA_COLLECTION);
    }

    @Override
    public ServerKeys getServerKeys() throws Exception {
        return mongoDB.getServerKeys(ConstantClass.STORE_KEYS);
    }

    @Override
    public List<String> decryptData(ArrayList<ArrayList<byte[]>> list, ServerKeys serverKeys)
            throws Exception {
        List<String> stringList = new ArrayList<>();
        System.out.println("Decrypting data...and converting to string (file name):" + getClass());
        for (ArrayList<byte[]> val : list) {
            StringBuilder builder = new StringBuilder();
            for (byte[] encryptedVal : val) {
                String subString = commonFunctions.decryptData(encryptedVal, serverKeys.getPublicKeyModules(),
                        serverKeys.getPublicKeyExpo());
                builder.append(subString);
            }
            stringList.add(builder.toString());
        }
        return stringList;
    }

    @Override
    public SendData prepareDataToSend(List<String> list) throws Exception {

        SendData sendData = new SendData();
        BlockStructure block1 = commonFunctions.convertJsonToJava(list.get(1), BlockStructure.class);
        sendData.setProductID(block1.getBlockID());
        sendData.setCreationDate(block1.getDate());
        sendData.setCreationTime(block1.getTime());
        sendData.setPrice(block1.getPrice());
        sendData.setProductName(block1.getItemName());
        sendData.setQuantity(block1.getQuantity());
        sendData.setSupplierName(block1.getSupplierName());

        BlockStructure2 block2 = commonFunctions.convertJsonToJava(list.get(2), BlockStructure2.class);
        sendData.setMachineNumber(block2.getMachineNumber());
        sendData.setTemperature(block2.getTemperature());

        BlockStructure3 block3 = commonFunctions.convertJsonToJava(list.get(3), BlockStructure3.class);
        sendData.setTotalBoxes(block3.getTotalBoxes());
        sendData.setBoxNumberRange(block3.getBoxNumberRange());

        BlockStructure4 block4 = commonFunctions.convertJsonToJava(list.get(4), BlockStructure4.class);
        sendData.setTotalCarton(block4.getTotalCarton());
        sendData.setCartonNumberRange(block4.getCartonNumber());
        sendData.setExporterName(block4.getExporterName());
        sendData.setBlockPresent(list.size()-1);
        return sendData;
    }
}
