package com.github.joine.restapi.domain;

/**
 * @Author: JenphyJohn
 * @Date: 2019/6/2 12:13 AM
 */
public class RawUserBean {

    private String signature;

    private String rawData;

    private String encryptedData;

    private String iv;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
