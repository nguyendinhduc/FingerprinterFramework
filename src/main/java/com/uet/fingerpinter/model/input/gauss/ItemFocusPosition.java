package com.uet.fingerpinter.model.input.gauss;

public class ItemFocusPosition {
    private int x;
    private int y;
    private double deltaX;
    private double deltaY;
    private double distanceWithFocus;
    private double distribution;
    private int miss;

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

    public double getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public double getDistanceWithFocus() {
        return distanceWithFocus;
    }

    public void setDistanceWithFocus(double distanceWithFocus) {
        this.distanceWithFocus = distanceWithFocus;
    }

    public double getDistribution() {
        return distribution;
    }

    public void setDistribution(double distribution) {
        this.distribution = distribution;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }
}
