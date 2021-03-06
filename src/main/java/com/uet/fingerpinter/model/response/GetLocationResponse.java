package com.uet.fingerpinter.model.response;

public class GetLocationResponse {
    private int x;
    private int y;
    private int transactionId;

    public GetLocationResponse(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GetLocationResponse(int x, int y, int transactionId) {
        this.x = x;
        this.y = y;
        this.transactionId = transactionId;
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

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
