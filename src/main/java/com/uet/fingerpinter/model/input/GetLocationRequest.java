package com.uet.fingerpinter.model.input;

import java.util.List;

public class GetLocationRequest {
    private int buildingId;
    private int roomId;
    private List<InfoReferencePointRequest> infos;
    private ExtendGetLocationModel extendGetLocationModel;

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<InfoReferencePointRequest> getInfos() {
        return infos;
    }

    public void setInfos(List<InfoReferencePointRequest> infos) {
        this.infos = infos;
    }

    public ExtendGetLocationModel getExtendGetLocationModel() {
        return extendGetLocationModel;
    }

    public void setExtendGetLocationModel(ExtendGetLocationModel extendGetLocationModel) {
        this.extendGetLocationModel = extendGetLocationModel;
    }
}
