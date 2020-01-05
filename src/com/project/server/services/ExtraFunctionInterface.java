package com.project.server.services;

public interface ExtraFunctionInterface {
    String generateSignature() throws Exception;
    String calculateHash(String value) throws Exception;
}
