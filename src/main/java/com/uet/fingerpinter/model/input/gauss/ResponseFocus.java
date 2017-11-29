package com.uet.fingerpinter.model.input.gauss;

public class ResponseFocus {
    private float x;
    private float y;
    private int transactionId;

    public ResponseFocus(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public ResponseFocus(float x, float y, int transactionId) {
        this.x = x;
        this.y = y;
        this.transactionId = transactionId;
    }

    public ResponseFocus() {
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
