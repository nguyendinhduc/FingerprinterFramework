package com.uet.fingerpinter.model.input.gauss;

public class DistributionGauss {
    private int x;
    private int y;
    private int numberMiss;

    private double distribution;

    public DistributionGauss(int x, int y, double distribution, int numberMiss) {
        this.x = x;
        this.y = y;
        this.distribution = distribution;
        this.numberMiss = numberMiss;
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

    public double getDistribution() {
        return distribution;
    }

    public void setDistribution(double distribution) {
        this.distribution = distribution;
    }

    public int getNumberMiss() {
        return numberMiss;
    }

    public void setNumberMiss(int numberMiss) {
        this.numberMiss = numberMiss;
    }
}
