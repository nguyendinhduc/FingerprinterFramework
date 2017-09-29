package com.uet.fingerpinter.model.input;

import java.util.List;

public class GetLocationRequest {
    private int buidingId;
    private int roomId;
    private List<InfoReferencePointRequest> infos;

    public int getBuidingId() {
        return buidingId;
    }

    public void setBuidingId(int buidingId) {
        this.buidingId = buidingId;
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
}
