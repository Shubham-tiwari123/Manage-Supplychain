package com.project.server1.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.math.BigInteger;

@JsonPropertyOrder({
        "clientPubKeyMod","clientPubKeyExpo"
})
public class ClientKeys implements Serializable {

    private BigInteger clientPubKeyMod ;
    private BigInteger clientPubKeyExpo ;

    public BigInteger getClientPubKeyMod() {
        return clientPubKeyMod;
    }

    public void setClientPubKeyMod(BigInteger clientPubKeyMod) {
        this.clientPubKeyMod = clientPubKeyMod;
    }

    public BigInteger getClientPubKeyExpo() {
        return clientPubKeyExpo;
    }

    public void setClientPubKeyExpo(BigInteger clientPubKeyExpo) {
        this.clientPubKeyExpo = clientPubKeyExpo;
    }
}
