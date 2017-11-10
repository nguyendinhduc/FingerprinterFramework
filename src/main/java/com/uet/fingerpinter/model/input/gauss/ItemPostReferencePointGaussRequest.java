package com.uet.fingerpinter.model.input.gauss;


import javax.validation.Valid;
import java.util.List;

public class ItemPostReferencePointGaussRequest {
    private String appName;

    @Valid
    private String macAddress;

    @Valid
    private List<Float> listRss;


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public List<Float> getListRss() {
        return listRss;
    }

    public void setListRss(List<Float> listRss) {
        this.listRss = listRss;
    }
}
