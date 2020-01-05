package com.project.server.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class ExtraFunction implements ExtraFunctionInterface{
    @Override
    public String generateSignature() throws Exception {
        return null;
    }

    @Override
    public String calculateHash(String value) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHashValue = digest.digest(value.getBytes(StandardCharsets.UTF_8));
        StringBuilder hashValue = new StringBuilder(2 * encodedHashValue.length);
        for (byte b : encodedHashValue) {
            hashValue.append(b);
        }
        return hashValue.toString();
    }
}
