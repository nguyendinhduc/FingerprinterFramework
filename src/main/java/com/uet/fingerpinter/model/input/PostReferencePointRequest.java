package com.uet.fingerpinter.model.input;

import java.util.List;

public class PostReferencePointRequest {
    private int buildingId;
    private int roomId;
    private int x;
    private int y;
    private List<InfoReferencePointRequest> infos;

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<InfoReferencePointRequest> getInfos() {
        return infos;
    }

    public void setInfos(List<InfoReferencePointRequest> infos) {
        this.infos = infos;
    }
}
