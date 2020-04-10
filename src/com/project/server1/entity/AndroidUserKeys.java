package com.project.server1.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.math.BigInteger;

@JsonPropertyOrder({
        "publicKeyModules","publicKeyExpo","privateKeyModules","privateKeyExpo"
})
public class AndroidUserKeys implements Serializable {
    private BigInteger publicKeyModules ;
    private BigInteger publicKeyExpo ;
    private BigInteger privateKeyModules;
    private BigInteger privateKeyExpo;

    public BigInteger getPublicKeyModules() {
        return publicKeyModules;
    }

    public void setPublicKeyModules(BigInteger publicKeyModules) {
        this.publicKeyModules = publicKeyModules;
    }

    public BigInteger getPublicKeyExpo() {
        return publicKeyExpo;
    }

    public void setPublicKeyExpo(BigInteger publicKeyExpo) {
        this.publicKeyExpo = publicKeyExpo;
    }

    public BigInteger getPrivateKeyModules() {
        return privateKeyModules;
    }

    public void setPrivateKeyModules(BigInteger privateKeyModules) {
        this.privateKeyModules = privateKeyModules;
    }

    public BigInteger getPrivateKeyExpo() {
        return privateKeyExpo;
    }

    public void setPrivateKeyExpo(BigInteger privateKeyExpo) {
        this.privateKeyExpo = privateKeyExpo;
    }
}
