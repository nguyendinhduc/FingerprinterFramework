package com.uet.fingerpinter.model.input;

public class InfoReferencePointRequest {
    private String macAddress;
    private String name;
    private float rss;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRss() {
        return rss;
    }

    public void setRss(float rss) {
        this.rss = rss;
    }
}
