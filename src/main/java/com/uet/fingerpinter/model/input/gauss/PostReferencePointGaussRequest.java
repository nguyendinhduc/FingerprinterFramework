package com.uet.fingerpinter.model.input.gauss;

import javax.validation.Valid;
import java.util.List;

public class PostReferencePointGaussRequest {
    @Valid
    private int roomId;

    @Valid
    private int x;

    @Valid
    private int y;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Valid
    private List<ItemPostReferencePointGaussRequest> itemPostReferencePointGaussRequests;


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

    public List<ItemPostReferencePointGaussRequest> getItemPostReferencePointGaussRequests() {
        return itemPostReferencePointGaussRequests;
    }

    public void setItemPostReferencePointGaussRequests(List<ItemPostReferencePointGaussRequest> itemPostReferencePointGaussRequests) {
        this.itemPostReferencePointGaussRequests = itemPostReferencePointGaussRequests;
    }
}
